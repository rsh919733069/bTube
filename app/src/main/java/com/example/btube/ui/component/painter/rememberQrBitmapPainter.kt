package com.example.btube.ui.component.painter

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 将字符串编码为二维码 BitmapPainter
 */
@Composable
fun rememberQrBitmapPainter(
    content: String,
    size: Dp = 200.dp,
    padding: Dp = 0.dp
): BitmapPainter {
    val density = LocalDensity.current
    val sizePx = with(density) { size.roundToPx() }
    val paddingPx = with(density) { padding.roundToPx() }

    var bitmap by remember(content) { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(content) {
        if (content.isBlank()) return@LaunchedEffect
        bitmap = withContext(Dispatchers.IO) {
            val qrCodeWriter = QRCodeWriter()
            val encodeHints = mutableMapOf<EncodeHintType, Any?>().apply {
                this[EncodeHintType.MARGIN] = paddingPx
            }
            val bitmapMatrix = try {
                qrCodeWriter.encode(
                    content,
                    BarcodeFormat.QR_CODE,
                    sizePx,
                    sizePx,
                    encodeHints
                )
            } catch (_: WriterException) {
                null
            }
            val w = bitmapMatrix?.width ?: sizePx
            val h = bitmapMatrix?.height ?: sizePx
            createBitmap(w, h).apply {
                for (x in 0 until w) {
                    for (y in 0 until h) {
                        val shouldColorPixel = bitmapMatrix?.get(x, y) == true
                        this[x, y] = if (shouldColorPixel) Color.BLACK else Color.WHITE
                    }
                }
            }
        }
    }

    return remember(bitmap) {
        val currentBitmap = bitmap ?: createBitmap(sizePx, sizePx).apply {
            eraseColor(Color.TRANSPARENT)
        }
        BitmapPainter(currentBitmap.asImageBitmap())
    }
}
