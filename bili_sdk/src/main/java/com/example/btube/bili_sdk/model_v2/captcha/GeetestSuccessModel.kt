package com.example.btube.bili_sdk.model_v2.captcha

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 极验验证码成功回调模型
 * 用于短信登录时传递验证结果
 */
@Serializable
data class GeetestSuccessModel(
    @SerialName("geetest_challenge") val geetestChallenge: String = "",
    @SerialName("geetest_seccode") val geetestSeccode: String = "",
    @SerialName("geetest_validate") val geetestValidate: String = ""
)
