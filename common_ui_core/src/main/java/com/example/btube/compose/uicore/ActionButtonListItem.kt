package com.example.btube.compose.uicore

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private val ActionWidth = 56.dp
private val ActionsRegionWidth = 168.dp // 约3个按钮

/**
 * 带滑动操作按钮的列表项
 * 左滑显示操作按钮（分享、删除、收藏等）
 */
@Composable
fun ActionButtonListItem(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    onOpenChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable @UiComposable () -> Unit,
    actions: @Composable @UiComposable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val maxOffsetX = with(density) { ActionsRegionWidth.roundToPx() }.toFloat()

    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(isOpen) {
        offsetX.animateTo(
            targetValue = if (isOpen) -maxOffsetX else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures {
                    if (isOpen) {
                        onOpenChange(false)
                    } else {
                        onClick()
                    }
                }
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            val newOffset = (offsetX.value + dragAmount).coerceIn(-maxOffsetX, 0f)
                            offsetX.snapTo(newOffset)
                            onOpenChange(newOffset < -maxOffsetX * 0.3f)
                        }
                    }
                )
            }
    ) {
        // 操作按钮区域（底层，右对齐，滑动后露出）
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .then(Modifier.size(width = ActionsRegionWidth, height = ActionWidth)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }
        // 主内容（顶层，可左滑）
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .background(MaterialTheme.colorScheme.surface)
        ) {
            content()
        }
    }
}

/**
 * 操作按钮项 - 用于 ActionButtonListItem 的 actions 中
 */
@Composable
fun ActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
