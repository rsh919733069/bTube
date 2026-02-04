package com.example.btube.bili_sdk.model_v2.history

import com.example.btube.bili_sdk.model_v2.video.VideoView
import kotlinx.serialization.Serializable

@Serializable
data class ToViewModel(
    val count: Int,
    val list: List<VideoView>
) {
    companion object {
        val EMPTY = ToViewModel(0, emptyList())
    }
}
