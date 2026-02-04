package com.example.btube.core

import android.Manifest
import android.os.Build
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * 应用全局常量定义
 */

// ============ DataStore Keys ============
/** 登录状态 */
val IS_LOGIN_KEY = booleanPreferencesKey("is_login")

/** Cookie */
val COOKIE_KEY = stringPreferencesKey("cookie")

/** 刷新令牌 */
val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")

/** WBI 图片URL密钥 */
val IMG_URL_KEY = stringPreferencesKey("img_url")

/** WBI 子URL密钥 */
val SUB_URL_KEY = stringPreferencesKey("sub_url")

/** 用户头像URL */
val FACE_URL_KEY = stringPreferencesKey("face_url")

/** 用户名 */
val USERNAME_KEY = stringPreferencesKey("username")

/** UP主MID */
val UP_MID_KEY = stringPreferencesKey("up_mid")

/** VIP状态 */
val VIP_STATUS_KEY = stringPreferencesKey("vip_status")

/** 上次显示列表 */
val LAST_SHOW_LIST_KEY = stringPreferencesKey("last_show_list")

// ============ SharedPreferences Keys ============
/** 自动跳过 */
const val AUTO_SKIP_KEY = "auto_skip"

/** 合并资源 */
const val MERGE_SOURCE_KEY = "merge_source"

/** 移动网络视频质量 */
internal const val MOBILE_NET_VIDEO_QUALITY = "mobile_net_video_quality"

/** WLAN视频质量 */
internal const val WLAN_VIDEO_QUALITY = "wlan_video_quality"

/** 移动网络音频质量 */
internal const val MOBILE_NET_AUDIO_QUALITY = "mobile_net_audio_quality"

/** WLAN音频质量 */
internal const val WLAN_AUDIO_QUALITY = "wlan_audio_quality"

/** 导出共享资源 */
const val EXPORT_SHARED_SOURCE = "export_shared_source"

/** 保存崩溃文档 */
internal const val SAVE_CRASH_DOCS_KEY = "save_crash_docs_key"

/** 共享文件名 */
const val SHARED_FILE = "btube_shared"

// ============ 权限定义 ============
/** 读取存储权限 */
internal val READ_STORAGE_PERMISSION = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    listOf(
        Manifest.permission.READ_MEDIA_VIDEO,
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_AUDIO
    )
} else {
    listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
}

/** 写入存储权限 */
internal val WRITE_STORAGE_PERMISSION = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    listOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
} else {
    listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}

/** 通知权限 */
internal val NOTIFICATION_PERMISSION = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    listOf(Manifest.permission.POST_NOTIFICATIONS)
} else {
    emptyList()
}

// ============ 视频/音频质量配置 ============
/** 普通音频质量 */
internal val NormalAudioQuality = listOf(30216, 30232, 30280)

/** 视频超高质量 */
internal val VideoSuperQualities = listOf(
    Pair(127, "8K 超高清"),
    Pair(126, "杜比视界"),
    Pair(125, "HDR 真彩色"),
    Pair(120, "4K 超清"),
    Pair(116, "1080P60 高帧率"),
    Pair(112, "1080P 高码率"),
)

/** 视频质量映射表 */
internal val VideoQualityMap = mapOf(
    127 to "8K 超高清",
    126 to "杜比视界",
    125 to "HDR 真彩色",
    120 to "4K 超清",
    116 to "1080P60 高帧率",
    112 to "1080P 高码率",
    80 to "1080P 高清",
    74 to "720P60 高帧率",
    64 to "720P 高清",
    32 to "480P 清晰",
    16 to "360P 流畅",
)

/** 音频质量映射表 */
internal val AudioQualityMap = mapOf(
    30251 to "Hi-Res无损",
    30250 to "杜比全景声",
    30280 to "高质量",
    30232 to "中质量",
    30216 to "低质量",
)

/** 视频质量列表 */
internal val VideoQualities = listOf(
    Pair(Int.MAX_VALUE, "超高画质"),
    Pair(80, "1080P 高清"),
    Pair(74, "720P60 高帧率"),
    Pair(64, "720P 高清"),
    Pair(32, "480P 清晰"),
    Pair(16, "360P 流畅"),
)

/** 音频质量列表 */
internal val AudioQualities = listOf(
    Pair(30251, "Hi-Res无损"),
    Pair(30250, "杜比全景声"),
    Pair(30280, "高质量"),
    Pair(30232, "中质量"),
    Pair(30216, "低质量"),
)

// ============ 其他常量 ============
/** 中国国家代码 */
internal const val ChainCountryId = "86"

/** 关键词高亮正则表达式 */
internal val KeywordPattern = "<em class=\"keyword\">(.*?)</em>".toRegex()

/** Alpha透明度 */
internal const val AlphaFraction = 0.98f
