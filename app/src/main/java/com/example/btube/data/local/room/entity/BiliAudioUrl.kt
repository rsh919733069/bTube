package com.example.btube.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_audio_urls",
    primaryKeys = ["bvid", "quality"]
)
data class BiliAudioUrl(
    val bvid: String,
    val aid: Long?,
    val cid: Long?,
    val quality: Int,
    val url: String
)
