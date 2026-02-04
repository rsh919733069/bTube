package com.example.btube.bili_sdk.model_v2.common

import kotlinx.serialization.Serializable

@Serializable
data class Dimension(
    val width: Int = 0,
    val height: Int = 0,
    val rotate: Int = 0
)
