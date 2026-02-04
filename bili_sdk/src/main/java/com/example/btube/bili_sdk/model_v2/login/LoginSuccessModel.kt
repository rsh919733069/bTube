package com.example.btube.bili_sdk.model_v2.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginSuccessModel(
    @SerialName("is_new") val isNew: Boolean = false,
    val status: Int = 0,
    val message: String = "",
    val url: String = "",
    @SerialName("refresh_token") val refreshToken: String = "",
    val timestamp: Long = 0L,
    val hint: String = ""
)
