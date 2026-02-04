package com.example.btube.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btube.AppState
import com.example.btube.model.play.MediaPlayConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

/**
 * 应用级共享 ViewModel
 */
class SharedViewModel : ViewModel() {

    private val _appState = MutableStateFlow(AppState())

    val appState = _appState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _appState.value
    )

    /** 当前待播放的视频配置，点击视频卡片时设置，播放页读取 */
    var mPlayParam: MediaPlayConfig = MediaPlayConfig.NONE
        private set

    fun updateAppState(newState: AppState) {
        _appState.update { newState }
    }

    fun setPlayParam(playParam: MediaPlayConfig) {
        mPlayParam = playParam
    }
}
