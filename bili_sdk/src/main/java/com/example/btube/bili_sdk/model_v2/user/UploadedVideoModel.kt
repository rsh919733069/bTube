package com.example.btube.bili_sdk.model_v2.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadedVideoModel(
    val count: Int = 0,
    @SerialName("has_next") val hasNext: Boolean = false,
    @SerialName("has_prev") val hasPrev: Boolean = false,
    @SerialName("item") val items: List<UploadedVideoItem> = emptyList()
)

@Serializable
data class UploadedVideoItem(
    val title: String = "",
    val subtitle: String = "",
    val tname: String = "",
    val cover: String = "",
    val uri: String = "",
    @SerialName("param") val aid: String = "",
    val goto: String = "",
    val duration: Long = 0L,
    val play: Long = 0L,
    val danmaku: Long = 0L,
    val ctime: Long = 0L,
    val author: String = "",
    val bvid: String = "",
    val videos: Int = 0,
    @SerialName("first_cid") val cid: Long = 0L,
    @SerialName("view_content") val viewContent: String = "",
    @SerialName("publish_time_text") val publishTimeText: String = ""
)
