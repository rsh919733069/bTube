package com.example.btube.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.btube.data.local.room.converter.RoomTypeConverters
import com.example.btube.data.local.room.dao.BiliSharedSourceDao
import com.example.btube.data.local.room.dao.DownloadTaskDao
import com.example.btube.data.local.room.dao.SearchHistoryDao
import com.example.btube.data.local.room.entity.BiliAudioUrl
import com.example.btube.data.local.room.entity.BiliVideoUrl
import com.example.btube.data.local.room.entity.DownloadTask
import com.example.btube.data.local.room.entity.SearchHistory

@Database(
    entities = [
        SearchHistory::class,
        DownloadTask::class,
        BiliVideoUrl::class,
        BiliAudioUrl::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomTypeConverters::class)
abstract class BTubeDB : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun downloadTaskDao(): DownloadTaskDao
    abstract fun biliSharedSourceDao(): BiliSharedSourceDao

    companion object {
        @Volatile
        private var INSTANCE: BTubeDB? = null

        fun getInstance(context: Context): BTubeDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): BTubeDB {
            return Room.databaseBuilder(
                context.applicationContext,
                BTubeDB::class.java,
                "btube.db"
            ).build()
        }
    }
}
