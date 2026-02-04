package com.example.btube.bili_sdk.model_v2.video

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class VideoView(
    val bvid: String,
    val aid: Long,
    val cid: Long,
    val pic: String,
    val title: String,
    val pubdate: Long,
    val ctime: Long,
    val desc: String,
    val duration: Long,
    val owner: VideoOwner,
    val stat: VideoStat,
    val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class VideoOwner(
    val mid: Long,
    val name: String,
    val face: String
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class VideoStat(
    val aid: Long = 0L,
    val view: Long = 0L,
    val danmaku: Long = 0L,
    val reply: Long = 0L,
    val favorite: Long = 0L,
    val coin: Long = 0L,
    val share: Long = 0L,
    val like: Long = 0L,
    val dislike: Long = 0L
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class VideoDimension(
    val width: Int,
    val height: Int,
    val rotate: Int
)
