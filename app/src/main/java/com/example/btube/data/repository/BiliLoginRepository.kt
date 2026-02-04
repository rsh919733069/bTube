package com.example.btube.data.repository

import android.content.Context
import com.example.btube.bili_sdk.apis.AuthApi
import com.example.btube.bili_sdk.model.BiliQRCodeStatus
import com.example.btube.bili_sdk.model_v2.login.LoginSuccessModel
import com.example.btube.core.COOKIE_KEY
import com.example.btube.data.local.datastore.dataStore
import io.ktor.http.Headers
import kotlinx.coroutines.flow.firstOrNull

/**
 * 登录相关 Repository
 */
class BiliLoginRepository(
    private val authApi: AuthApi,
    private val context: Context
) {

    suspend fun requestQRCode() = authApi.requestQRCode().data

    suspend fun checkScanStatus(
        qrcodeKey: String,
        headersCallback: suspend (Headers) -> Unit,
        resultCallback: suspend (BiliQRCodeStatus) -> Unit
    ) {
        val status = authApi.checkScanStatus(
            qrcodeKey = qrcodeKey,
            saveCookieCallback = { headers -> headersCallback(headers) }
        )
        resultCallback(status)
    }

    suspend fun getCaptcha(source: String = AuthApi.LOGIN_SOURCE_HEADER) =
        authApi.getCaptcha(source = source)

    suspend fun sendSMSCode(
        cid: String,
        tel: String,
        source: String = AuthApi.LOGIN_SOURCE_HEADER,
        token: String,
        challenge: String,
        validate: String,
        seccode: String
    ) = authApi.sendSMSCode(
        cookie = context.dataStore.data.firstOrNull()?.get(COOKIE_KEY),
        cid = cid,
        tel = tel,
        source = source,
        token = token,
        challenge = challenge,
        validate = validate,
        seccode = seccode
    )

    suspend fun smsLogin(
        cid: String,
        tel: String,
        code: String,
        source: String = AuthApi.LOGIN_SOURCE_HEADER,
        captchaKey: String,
        goUrl: String? = null,
        keep: Boolean = true,
        headersCallback: suspend (Headers) -> Unit,
        resultCallback: suspend (LoginSuccessModel) -> Unit
    ): String = authApi.smsLogin(
        cid = cid,
        tel = tel,
        code = code,
        source = source,
        captchaKey = captchaKey,
        goUrl = goUrl,
        keep = keep,
        headersCallback = headersCallback,
        resultCallback = resultCallback
    )
}
