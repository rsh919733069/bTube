package com.example.btube.features.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.btube.ui.component.painter.rememberQrBitmapPainter
import com.example.btube.ui.theme.BiliPink

/**
 * 二维码登录页
 */
@Composable
fun QRCodeLoginScreen(
    uiState: LoginUIState,
    requestQrcode: () -> Unit,
    onCancel: () -> Unit,
    upPress: () -> Unit,
    onSwitchToSms: (() -> Unit)? = null
) {
    DisposableEffect(Unit) {
        requestQrcode()
        onDispose { onCancel() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = upPress,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "跳过登录"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "扫码登录",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "请使用哔哩哔哩 App 扫描二维码",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedContent(
                targetState = uiState.qrCode == null,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "qrcode"
            ) { isLoading ->
                val shape = RoundedCornerShape(12.dp)
                val modifier = Modifier
                    .size(200.dp)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                if (isLoading) {
                    Box(
                        modifier = modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = BiliPink)
                    }
                } else {
                    androidx.compose.foundation.Image(
                        painter = rememberQrBitmapPainter(
                            content = uiState.qrCode?.url ?: "",
                            size = 200.dp
                        ),
                        contentDescription = "登录二维码",
                        modifier = modifier
                    )
                }
            }

            onSwitchToSms?.let { switch ->
                Spacer(modifier = Modifier.height(24.dp))
                Button (onClick = switch) {
                    Text("短信登录")
                }
            }
        }
    }
}
