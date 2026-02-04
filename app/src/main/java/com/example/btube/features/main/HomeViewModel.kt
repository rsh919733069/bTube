package com.example.btube.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.btube.data.repository.BiliHomeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

class HomeViewModel(
    private val biliHomeRepository: BiliHomeRepository
) : ViewModel() {

    val recommends = biliHomeRepository.getRecommendPager()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1)
        .cachedIn(viewModelScope)

    val hots = biliHomeRepository.getHotPager()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1)
        .cachedIn(viewModelScope)
}
