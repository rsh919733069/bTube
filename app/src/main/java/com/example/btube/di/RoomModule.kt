package com.example.btube.di

import com.example.btube.data.local.room.BTubeDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Room 数据库依赖注入模块
 */
val roomModule = module {
    single { BTubeDB.getInstance(androidContext()) }
    single { get<BTubeDB>().searchHistoryDao() }
    single { get<BTubeDB>().downloadTaskDao() }
    single { get<BTubeDB>().biliSharedSourceDao() }
}
