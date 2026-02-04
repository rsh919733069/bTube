package com.example.btube.bili_sdk.model_v2.user

import com.example.btube.bili_sdk.model_v2.common.LevelInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoCardModel(
    val card: InfoCard = InfoCard(),
    val following: Boolean = false,
    @SerialName("archive_count") val archiveCount: Int = 0,
    val follower: Long = 0L,
    @SerialName("like_num") val likeNum: Long = 0L
) {
    companion object {
        val ERROR = InfoCardModel()
    }
}

@Serializable
data class InfoCard(
    val mid: String = "",
    val name: String = "",
    val sex: String = "",
    val rank: String = "",
    val face: String = "",
    val birthday: String = "",
    val fans: Long = 0L,
    val friend: Long = 0L,
    val attention: Long = 0L,
    val sign: String = "",
    @SerialName("Official") val official: OfficialModel = OfficialModel(),
    @SerialName("official_verify") val officialVerify: OfficialModel = OfficialModel(),
    @SerialName("level_info") val levelInfo: LevelInfo = LevelInfo()
)

@Serializable
data class OfficialModel(
    val role: Int = 1,
    val title: String = "",
    val desc: String = "",
    val type: Int = 0
)
