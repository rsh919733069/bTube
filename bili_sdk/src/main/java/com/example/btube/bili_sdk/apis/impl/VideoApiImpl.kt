package com.example.btube.bili_sdk.apis.impl

import com.example.btube.bili_sdk.apis.URL_HOT
import com.example.btube.bili_sdk.apis.URL_RECOMMEND
import com.example.btube.bili_sdk.apis.VideoApi
import com.example.btube.bili_sdk.exception.globalSDKExceptionHandle
import com.example.btube.bili_sdk.model_v2.common.BiliResponse
import com.example.btube.bili_sdk.model_v2.hot.HotModel
import com.example.btube.bili_sdk.model_v2.recommend.RecommendModel
import com.example.btube.bili_sdk.wbi.WbiParams
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class VideoApiImpl(private val client: HttpClient) : VideoApi {

    override suspend fun getRecommends(
        cookie: String?,
        refreshType: Int,
        lastShowList: String?,
        feedVersion: String,
        freshIdx: Int,
        freshIdx1h: Int,
        brush: Int,
        webLocation: Int,
        homepageVer: Int,
        fetchRow: Int,
        ps: Int,
        yNum: Int
    ): BiliResponse<RecommendModel> = withContext(Dispatchers.IO) {
        runCatching {
            val url = if (WbiParams.wbi != null) {
                val param = WbiParams.wbi!!.enc(
                    mapOf(
                        "fresh_type" to refreshType,
                        "fresh_idx" to freshIdx,
                        "fresh_idx_1h" to freshIdx1h,
                        "web_location" to webLocation,
                        "y_num" to yNum,
                        "homepage_ver" to homepageVer,
                        "feed_version" to feedVersion,
                        "brush" to brush,
                        "fetch_row" to fetchRow,
                        "ps" to ps,
                        "last_showlist" to lastShowList
                    )
                )
                "${URL_RECOMMEND}?$param"
            } else {
                URL_RECOMMEND
            }
            val response = client.get(url) {
                cookie?.apply { header(HttpHeaders.Cookie, this) }
            }
            Json.decodeFromString<BiliResponse<RecommendModel>>(response.bodyAsText())
        }.fold(
            onSuccess = { it },
            onFailure = {
                globalSDKExceptionHandle("VideoApiImpl", it)
                BiliResponse(code = 400, message = "ERROR", data = RecommendModel())
            }
        )
    }

    override suspend fun getHots(
        cookie: String?,
        pn: Int,
        ps: Int
    ): BiliResponse<HotModel> = withContext(Dispatchers.IO) {
        runCatching {
            val response = client.get(URL_HOT) {
                cookie?.apply { header(HttpHeaders.Cookie, this) }
                parameter("pn", pn.toString())
                parameter("ps", ps.toString())
            }
            Json.decodeFromString<BiliResponse<HotModel>>(response.bodyAsText())
        }.fold(
            onSuccess = { it },
            onFailure = {
                globalSDKExceptionHandle("VideoApiImpl", it)
                BiliResponse(code = 400, message = "ERROR", data = HotModel())
            }
        )
    }
}
