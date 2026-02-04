package com.example.btube.bili_sdk.model_v2.recommend

import com.example.btube.bili_sdk.model_v2.video.VideoOwner
import com.example.btube.bili_sdk.model_v2.video.VideoStat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendModel(
    val item: List<RecommendItem> = emptyList(),
    val mid: Long = -1
)

@Serializable
data class RecommendItem(
    val id: Long = 0,
    val bvid: String = "",
    val cid: Long = 0,
    val goto: String = "",
    val uri: String = "",
    val pic: String = "",
    @SerialName("pic_4_3") val pic43: String = "",
    val title: String = "",
    val duration: Long = 0,
    @SerialName("pubdate") val pubDate: Long = 0,
    val owner: VideoOwner? = null,
    val stat: VideoStat? = null,
    @SerialName("is_followed") val isFollowed: Int = 0
)
