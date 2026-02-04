package com.example.btube.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.btube.data.local.room.entity.DownloadTask

@Dao
interface DownloadTaskDao {

    @Query("SELECT * FROM tb_downloads")
    suspend fun getAllTasks(): List<DownloadTask>

    @Query("SELECT * FROM tb_downloads WHERE id = :id")
    suspend fun getTaskById(id: String): DownloadTask?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: DownloadTask)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: DownloadTask)

    @Delete
    suspend fun deleteTasks(tasks: List<DownloadTask>)
}
