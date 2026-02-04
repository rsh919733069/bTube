package com.example.btube.bili_sdk.di

import com.example.btube.bili_sdk.apis.AuthApi
import com.example.btube.bili_sdk.apis.impl.AuthApiImpl
import com.example.btube.bili_sdk.apis.impl.UserApiImpl
import com.example.btube.bili_sdk.apis.UserApi
import org.koin.dsl.module

/**
 * bili_sdk 依赖注入模块
 * 供 app 模块使用
 */
val sdkModule = module {
    single<AuthApi> { AuthApiImpl(get()) }
    single<UserApi> { UserApiImpl(get()) }
}
