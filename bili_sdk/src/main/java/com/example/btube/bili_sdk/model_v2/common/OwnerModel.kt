package com.example.btube.bili_sdk.model_v2.common

import kotlinx.serialization.Serializable

@Serializable
data class OwnerModel(
    val mid: Long = 0,
    val name: String = "",
    val face: String = ""
)
