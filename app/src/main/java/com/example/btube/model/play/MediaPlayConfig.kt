package com.example.btube.model.play

/**
 * 视频播放配置
 * 用于在点击视频卡片时传递播放参数给播放页
 */
sealed class MediaPlayConfig(
    open val bvid: String = "",
    open val aid: Long = -1L,
    open val cid: Long = -1L,
    open val width: Int = 1920,
    open val height: Int = 1080,
    open val isLocal: Boolean = false,
) {
    /** 普通视频播放配置 */
    data class BasicVideoConfig(
        override val bvid: String,
        override val aid: Long = -1L,
        override val cid: Long = -1L,
        override val width: Int = 1920,
        override val height: Int = 1080,
        override val isLocal: Boolean = false,
    ) : MediaPlayConfig(bvid, aid, cid, width, height, isLocal)

    /** 番剧播放配置（待阶段7实现） */
    data class BangumiPlayConfig(
        override val bvid: String,
        override val aid: Long = -1L,
        override val cid: Long = -1L,
        val mediaId: Long? = null,
        val seasonId: Long? = null,
        val epId: Long? = null,
        override val width: Int = 1920,
        override val height: Int = 1080,
        override val isLocal: Boolean = false,
    ) : MediaPlayConfig(bvid, aid, cid, width, height, isLocal)

    object NONE : MediaPlayConfig()
}
