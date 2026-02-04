package com.example.btube.di

import com.example.btube.data.repository.BiliHomeRepository
import com.example.btube.data.repository.BiliLoginRepository
import com.example.btube.data.repository.SearchHistoryRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * 数据层依赖注入模块
 */
val dataModule = module {
    single { SearchHistoryRepository(get()) }
    single { BiliLoginRepository(get(), androidContext()) }
    single { BiliHomeRepository(androidContext(), get()) }
}
