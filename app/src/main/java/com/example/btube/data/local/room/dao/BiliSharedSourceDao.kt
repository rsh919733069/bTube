package com.example.btube.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.btube.data.local.room.entity.BiliAudioUrl
import com.example.btube.data.local.room.entity.BiliVideoUrl

@Dao
interface BiliSharedSourceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVideo(item: BiliVideoUrl)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVideos(items: List<BiliVideoUrl>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAudio(item: BiliAudioUrl)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAudios(items: List<BiliAudioUrl>)

    @Query("SELECT * FROM tb_video_urls WHERE bvid = :bvid")
    suspend fun getVideoUrlByBvid(bvid: String): List<BiliVideoUrl>

    @Query("SELECT * FROM tb_audio_urls WHERE bvid = :bvid")
    suspend fun getAudioUrlByBvid(bvid: String): List<BiliAudioUrl>

    @Query("DELETE FROM tb_video_urls WHERE bvid = :bvid")
    suspend fun deleteVideoUrls(bvid: String)

    @Query("DELETE FROM tb_audio_urls WHERE bvid = :bvid")
    suspend fun deleteAudioUrls(bvid: String)
}
