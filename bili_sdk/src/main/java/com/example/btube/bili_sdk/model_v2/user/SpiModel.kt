package com.example.btube.bili_sdk.model_v2.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpiModel(
    @SerialName("b_3") val b3: String = "",
    @SerialName("b_4") val b4: String = ""
)
