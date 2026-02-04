package com.example.btube.features.login

import com.example.btube.bili_sdk.model.BiliQRCode
import com.example.btube.bili_sdk.model_v2.captcha.CaptchaModel
import com.example.btube.bili_sdk.model_v2.captcha.GeetestSuccessModel
import com.example.btube.bili_sdk.model_v2.login.SMSCodeModel
import com.example.btube.core.ChainCountryId

/**
 * 登录页 UI 状态
 */
data class LoginUIState(
    // 二维码登录
    val qrCode: BiliQRCode? = null,

    // 短信登录
    val phoneNumber: String = "",
    val isPhoneNumberError: Boolean = false,
    val code: String = "",
    val isCodeError: Boolean = false,
    val smsCode: SMSCodeModel? = null,
    val selectedCountryId: String = ChainCountryId,

    // 验证码（短信登录需极验）
    val captchaModel: CaptchaModel? = null,
    val geetestSuccessModel: GeetestSuccessModel? = null,
)
