package com.example.btube.bili_sdk.apis.impl

import com.example.btube.bili_sdk.apis.URL_SPI
import com.example.btube.bili_sdk.apis.URL_USER_INFO_CARD
import com.example.btube.bili_sdk.apis.URL_USER_PROFILE
import com.example.btube.bili_sdk.apis.URL_USER_RELATION_MODIFY
import com.example.btube.bili_sdk.apis.URL_USER_STAT
import com.example.btube.bili_sdk.apis.URL_USER_UPLOADED_VIDEO
import com.example.btube.bili_sdk.apis.UserApi
import com.example.btube.bili_sdk.apis.UserRelationAction
import com.example.btube.bili_sdk.exception.globalSDKExceptionHandle
import com.example.btube.bili_sdk.model.BiliUserProfile
import com.example.btube.bili_sdk.model_v2.common.BiliResponse
import com.example.btube.bili_sdk.model_v2.common.BiliResponseNoData
import com.example.btube.bili_sdk.model_v2.user.InfoCardModel
import com.example.btube.bili_sdk.model_v2.user.SpiModel
import com.example.btube.bili_sdk.model_v2.user.UploadedVideoModel
import com.example.btube.bili_sdk.model_v2.user.UserStatModel
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class UserApiImpl(private val client: HttpClient) : UserApi {

    companion object {
        private val TAG = UserApiImpl::class.simpleName
        private const val DBG = true
    }

    override suspend fun postRelationModify(
        cookie: String?,
        mid: Long,
        act: UserRelationAction,
        csrf: String?
    ): BiliResponseNoData = withContext(Dispatchers.IO) {
        runCatching {
            val response = client.post(URL_USER_RELATION_MODIFY) {
                cookie?.apply { header(HttpHeaders.Cookie, cookie) }
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    FormDataContent(
                        Parameters.build {
                            append("fid", mid.toString())
                            append("act", act.id.toString())
                            csrf?.let { append("csrf", it) }
                        }
                    )
                )
            }
            Json.decodeFromString<BiliResponseNoData>(response.bodyAsText())
        }.fold(
            onSuccess = { it },
            onFailure = {
                if (DBG) globalSDKExceptionHandle(TAG, it)
                BiliResponseNoData.ERROR
            }
        )
    }

    override suspend fun getUploadedVideos(
        cookie: String?,
        vmid: Long,
        aid: Long?
    ): BiliResponse<UploadedVideoModel> = withContext(Dispatchers.IO) {
        runCatching {
            val response = client.get(URL_USER_UPLOADED_VIDEO) {
                cookie?.let { header(HttpHeaders.Cookie, it) }
                parameter("vmid", vmid.toString())
                aid?.let { parameter("aid", it.toString()) }
            }
            Json.decodeFromString<BiliResponse<UploadedVideoModel>>(response.bodyAsText())
        }.fold(
            onSuccess = { it },
            onFailure = {
                if (DBG) globalSDKExceptionHandle(TAG, it)
                BiliResponse(code = 400, message = "ERROR", data = UploadedVideoModel())
            }
        )
    }

    override suspend fun getUserProfile(cookie: String?): BiliResponse<BiliUserProfile>? =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = client.get(URL_USER_PROFILE) {
                    cookie?.apply { header(HttpHeaders.Cookie, this) }
                }
                Json.decodeFromString<BiliResponse<BiliUserProfile>>(response.bodyAsText())
            }.fold(
                onSuccess = { it },
                onFailure = {
                    if (DBG) globalSDKExceptionHandle(TAG, it)
                    null
                }
            )
        }

    override suspend fun getUserStat(cookie: String?): BiliResponse<UserStatModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = client.get(URL_USER_STAT) {
                    cookie?.apply { header(HttpHeaders.Cookie, this) }
                }
                Json.decodeFromString<BiliResponse<UserStatModel>>(response.bodyAsText())
            }.fold(
                onSuccess = { it },
                onFailure = {
                    if (DBG) globalSDKExceptionHandle(TAG, it)
                    BiliResponse(code = 400, message = "ERROR", data = UserStatModel())
                }
            )
        }

    override suspend fun getSpiInfo(cookie: String?): BiliResponse<SpiModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = client.get(URL_SPI) {
                    cookie?.apply { header(HttpHeaders.Cookie, this) }
                }
                Json.decodeFromString<BiliResponse<SpiModel>>(response.bodyAsText())
            }.fold(
                onSuccess = { it },
                onFailure = {
                    if (DBG) globalSDKExceptionHandle(TAG, it)
                    BiliResponse(code = 400, message = "ERROR", data = SpiModel())
                }
            )
        }

    override suspend fun getUserInfoCard(cookie: String?, mid: Long): BiliResponse<InfoCardModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = client.get(URL_USER_INFO_CARD) {
                    cookie?.apply { header(HttpHeaders.Cookie, this) }
                    parameter("mid", mid)
                }
                Json.decodeFromString<BiliResponse<InfoCardModel>>(response.bodyAsText())
            }.fold(
                onSuccess = { it },
                onFailure = {
                    if (DBG) globalSDKExceptionHandle(TAG, it)
                    BiliResponse(code = 400, message = "ERROR", data = InfoCardModel.ERROR)
                }
            )
        }
}
