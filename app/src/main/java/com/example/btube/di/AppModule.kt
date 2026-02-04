package com.example.btube.di

import com.example.btube.data.local.prefs.PreferencesUtil
import com.example.btube.network.HttpClientFactory
import com.example.btube.bili_sdk.di.sdkModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * 应用级依赖注入模块
 */
val appModule = module {
    // HTTP客户端
    single { HttpClientFactory.client }
    single { HttpClientFactory.coilClient }
    
    // SharedPreferences工具
    single { PreferencesUtil(androidContext()) }
    
    // 包含 bili_sdk 模块
    includes(sdkModule)
}
