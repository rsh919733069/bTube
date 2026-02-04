package com.example.btube.bili_sdk.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@JsonIgnoreUnknownKeys
@Serializable
data class BiliUserProfile(
    @SerialName("isLogin") val isLogin: Boolean = false,
    @SerialName("face") val face: String = "",
    @SerialName("mid") val mid: Long = 0L,
    @SerialName("uname") val uname: String = "",
    @SerialName("vipStatus") val vipStatus: Int = 0,
    @SerialName("wbi_img") val wbiImg: WbiImage? = null
)
