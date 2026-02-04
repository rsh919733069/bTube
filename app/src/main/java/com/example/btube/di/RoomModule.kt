package com.example.btube.di

import org.koin.dsl.module

/**
 * Room数据库依赖注入模块
 */
val roomModule = module {
    // TODO: 添加数据库和DAO实例
    // single {
    //     Room.databaseBuilder(
    //         androidContext(),
    //         BiliTubeDB::class.java,
    //         "btube_database"
    //     ).build()
    // }
    // 
    // single { get<BiliTubeDB>().searchHistoryDao() }
    // single { get<BiliTubeDB>().downloadTaskDao() }
    // ...
}
