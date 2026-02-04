package com.example.btube.bili_sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BiliWbi(
    @SerialName("isLogin") val isLogin: Boolean,
    @SerialName("wbi_img") val wbiImg: WbiImage
)

@Serializable
data class WbiImage(
    @SerialName("img_url") val imgUrl: String,
    @SerialName("sub_url") val subUrl: String
)
