package com.example.btube.model

import android.graphics.drawable.Drawable

/**
 * 分享目标应用模型
 */
data class ShareTarget(
    /** 应用名称 */
    val label: String,
    /** 包名 */
    val packageName: String,
    /** 应用图标 */
    val icon: Drawable
)
