package com.example.btube.bili_sdk.model_v2.hot

import com.example.btube.bili_sdk.model_v2.common.Dimension
import com.example.btube.bili_sdk.model_v2.common.OwnerModel
import com.example.btube.bili_sdk.model_v2.common.RightsModel
import com.example.btube.bili_sdk.model_v2.common.StatModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotModel(
    val list: List<HotItem> = emptyList(),
    @SerialName("no_more") val noMore: Boolean = false
)

@Serializable
data class HotItem(
    val aid: Long = 0,
    val videos: Int = 0,
    val tid: Int = 0,
    val tname: String = "",
    val tidv2: Int = 0,
    val tnamev2: String = "",
    val copyright: Int = 0,
    val pic: String = "",
    val title: String = "",
    val pubdate: Long = 0,
    val ctime: Long = 0,
    val desc: String = "",
    val state: Int = 0,
    val duration: Int = 0,
    @SerialName("mission_id") val missionId: Int = -1,
    val rights: RightsModel = RightsModel(),
    @SerialName("redirect_url") val redirectURL: String? = null,
    val owner: OwnerModel = OwnerModel(),
    val stat: StatModel = StatModel(),
    val dynamic: String = "",
    val cid: Long = 0,
    val dimension: Dimension = Dimension(),
    @SerialName("season_id") val seasonId: Int = -1,
    @SerialName("short_link_v2") val shortLinkV2: String = "",
    @SerialName("up_from_v2") val upFromV2: Int = -1,
    @SerialName("first_frame") val firstFrame: String = "",
    @SerialName("pub_location") val pubLocation: String = "",
    val cover43: String = "",
    val bvid: String = "",
    @SerialName("season_type") val seasonType: Int = 0,
    @SerialName("is_ogv") val isOgv: Boolean = false,
    @SerialName("enable_vt") val enableVt: Int = 0,
    @SerialName("ai_rcmd") val aiRcmd: String? = null,
    @SerialName("rcmd_reason") val rcmdReason: RcmdReasonModel = RcmdReasonModel()
)

@Serializable
data class RcmdReasonModel(
    val content: String? = null,
    @SerialName("corner_mark") val cornerMark: Int = -1,
    @SerialName("reason_type") val reasonType: Int = -1
)
