package com.example.btube.data.local.room.converter

import androidx.room.TypeConverter
import com.example.btube.data.local.room.entity.DownloadStatus

class RoomTypeConverters {

    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(";")

    @TypeConverter
    fun toList(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split(";")

    @TypeConverter
    fun fromDownloadStatus(status: DownloadStatus): Int = when (status) {
        DownloadStatus.PENDING -> 1
        DownloadStatus.DOWNLOADING -> 2
        DownloadStatus.PROCESSING -> 3
        DownloadStatus.COMPLETED -> 4
        DownloadStatus.FAILED -> 5
        DownloadStatus.PAUSE -> 6
    }

    @TypeConverter
    fun toDownloadStatus(status: Int): DownloadStatus = when (status) {
        2 -> DownloadStatus.DOWNLOADING
        3 -> DownloadStatus.PROCESSING
        4 -> DownloadStatus.COMPLETED
        5 -> DownloadStatus.FAILED
        6 -> DownloadStatus.PAUSE
        else -> DownloadStatus.PENDING
    }
}
