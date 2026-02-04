package com.example.btube.bili_sdk.model_v2.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SMSCodeModel(
    @SerialName("captcha_key") val captchaKey: String = ""
)
