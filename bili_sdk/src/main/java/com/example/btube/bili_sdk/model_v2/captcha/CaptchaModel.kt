package com.example.btube.bili_sdk.model_v2.captcha

import kotlinx.serialization.Serializable

@Serializable
data class GeetestModel(
    val gt: String = "",
    val challenge: String = ""
)

@Serializable
data class CaptchaModel(
    val type: String = "",
    val token: String = "",
    val geetest: GeetestModel = GeetestModel()
)
