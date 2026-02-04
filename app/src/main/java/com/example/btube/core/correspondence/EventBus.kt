package com.example.btube.core.correspondence

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * 应用级事件总线
 * 用于在不同组件间传递事件
 */
object EventBus {
    private val _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

    /**
     * 发送事件
     */
    suspend fun send(event: Event) {
        _events.emit(event)
    }
}

/**
 * 事件接口
 */
interface Event {
    /** 通知子刷新事件 */
    data object NotificationChildRefresh : Event

    /**
     * 视频播放器事件
     */
    sealed interface VideoPlayerEvent : Event {
        companion object {
            const val NONE_ACTION = 0
            const val FOLDER_ACTION = 1
        }

        /**
         * Snackbar消息事件
         */
        data class SnackbarEvent(
            val message: String,
            val actionType: Int = NONE_ACTION
        ) : VideoPlayerEvent

        /**
         * Snackbar消息事件（使用资源ID）
         */
        data class SnackbarEventById(
            @StringRes val messageId: Int,
            val actionType: Int = NONE_ACTION
        ) : VideoPlayerEvent
    }

    /**
     * 应用级事件
     */
    sealed interface AppEvent : Event {
        /**
         * Toast文本消息事件
         */
        data class ToastTextEvent(val message: String) : AppEvent
        
        /**
         * Toast消息事件（使用资源ID）
         */
        data class ToastEvent(@StringRes val messageId: Int) : AppEvent
        
        /**
         * 权限请求事件
         */
        data class PermissionRequestEvent(val permissions: List<String>) : AppEvent
    }
}
