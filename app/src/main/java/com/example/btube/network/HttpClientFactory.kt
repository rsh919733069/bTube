package com.example.btube.network

import androidx.media3.common.util.UnstableApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * HTTP客户端工厂
 * 提供统一配置的Ktor HttpClient实例
 */
@UnstableApi
object HttpClientFactory {
    /** 用户代理字符串 */
    const val USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36"
    
    /** Referer */
    const val REFERER = "https://www.bilibili.com"
    
    /**
     * 主HTTP客户端
     * 用于API请求，包含JSON序列化支持
     */
    val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                })
            }
            defaultRequest {
                header(HttpHeaders.UserAgent, USER_AGENT)
            }
        }
    }

    /**
     * Coil图片加载专用客户端
     * 简化配置，专注于图片下载
     */
    val coilClient: HttpClient by lazy {
        HttpClient(OkHttp)
    }
}
