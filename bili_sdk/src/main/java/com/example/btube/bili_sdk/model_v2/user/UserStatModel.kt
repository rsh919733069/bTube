package com.example.btube.bili_sdk.model_v2.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStatModel(
    val following: Int = 0,
    val follower: Int = 0,
    @SerialName("dynamic_count") val dynamicCount: Int = 0
)
