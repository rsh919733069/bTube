package com.example.btube.data.repository

import android.content.Context
import com.example.btube.bili_sdk.apis.AuthApi
import com.example.btube.bili_sdk.model.BiliQRCodeStatus
import androidx.datastore.preferences.core.edit
import com.example.btube.core.COOKIE_KEY
import com.example.btube.core.IS_LOGIN_KEY
import com.example.btube.core.REFRESH_TOKEN_KEY
import com.example.btube.data.local.datastore.dataStore
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.firstOrNull

/**
 * 登录相关 Repository
 * 负责登录 API 调用及 Cookie/登录状态持久化
 */
class BiliLoginRepository(
    private val authApi: AuthApi,
    private val context: Context
) {

    suspend fun requestQRCode() = authApi.requestQRCode().data

    /**
     * 轮询二维码扫描状态，登录成功后自动保存 Cookie 和登录状态
     */
    suspend fun checkScanStatus(
        qrcodeKey: String,
        onStatus: suspend (BiliQRCodeStatus) -> Unit
    ) {
        val status = authApi.checkScanStatus(
            qrcodeKey = qrcodeKey,
            saveCookieCallback = { headers -> saveCookieFromHeaders(headers) }
        )
        if (status.code == 0) {
            context.dataStore.edit { settings ->
                settings[REFRESH_TOKEN_KEY] = status.refreshToken
                settings[IS_LOGIN_KEY] = true
            }
        }
        onStatus(status)
    }

    private suspend fun saveCookieFromHeaders(headers: Headers) {
        val cookie = headers.getAll(HttpHeaders.SetCookie)?.joinToString("; ") ?: return
        context.dataStore.edit { settings ->
            settings[COOKIE_KEY] = cookie
        }
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

    /**
     * 短信验证码登录，成功后自动保存 Cookie 和登录状态
     */
    suspend fun smsLogin(
        cid: String,
        tel: String,
        code: String,
        source: String = AuthApi.LOGIN_SOURCE_HEADER,
        captchaKey: String,
        goUrl: String? = null,
        keep: Boolean = true
    ): String {
        val existingCookie = context.dataStore.data.firstOrNull()?.get(COOKIE_KEY)
        return authApi.smsLogin(
            cid = cid,
            tel = tel,
            code = code,
            source = source,
            captchaKey = captchaKey,
            goUrl = goUrl,
            keep = keep,
            headersCallback = { headers ->
                val cookieList = existingCookie?.split("; ")?.toMutableList() ?: mutableListOf()
                headers.getAll(HttpHeaders.SetCookie)?.let { cookieList.addAll(it) }
                context.dataStore.edit { settings ->
                    settings[COOKIE_KEY] = cookieList.distinct().joinToString("; ")
                }
            },
            resultCallback = { res ->
                context.dataStore.edit { settings ->
                    settings[REFRESH_TOKEN_KEY] = res.refreshToken
                    settings[IS_LOGIN_KEY] = true
                }
            }
        )
    }
}
