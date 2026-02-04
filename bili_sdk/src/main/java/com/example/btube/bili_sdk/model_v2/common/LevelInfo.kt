package com.example.btube.bili_sdk.model_v2.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LevelInfo(
    @SerialName("current_level") val currentLevel: Int = 0,
    @SerialName("current_min") val currentMin: Int = 0,
    @SerialName("current_exp") val currentExp: Int = 0,
    @SerialName("next_exp") val nextExp: Int = 0
)
