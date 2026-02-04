package com.example.btube.di

import com.example.btube.features.login.LoginViewModel
import com.example.btube.features.main.HomeViewModel
import com.example.btube.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel依赖注入模块
 */
val viewModelModule = module {
    viewModel { SharedViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    // TODO: 后续阶段添加
    // viewModel { HomeViewModel(get()) }
    // viewModel { MediaViewModel(get(), get()) }
}
