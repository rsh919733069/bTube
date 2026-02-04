package com.example.btube.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.btube.bili_sdk.apis.VideoApi
import com.example.btube.data.paging.HotPaging
import com.example.btube.data.paging.RecommendPaging
import com.example.btube.bili_sdk.model_v2.hot.HotItem
import com.example.btube.bili_sdk.model_v2.recommend.RecommendItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow

/**
 * 主页数据 Repository
 */
class BiliHomeRepository(
    private val context: Context,
    private val videoApi: VideoApi
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getRecommendPager(): Flow<PagingData<RecommendItem>> {
        return flow {
            emit(
                Pager(
                    config = PagingConfig(pageSize = 12, enablePlaceholders = false),
                    pagingSourceFactory = { RecommendPaging(videoApi, context) }
                ).flow
            )
        }.flattenConcat()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getHotPager(): Flow<PagingData<HotItem>> {
        return flow {
            emit(
                Pager(
                    config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                    pagingSourceFactory = { HotPaging(videoApi, context) }
                ).flow
            )
        }.flattenConcat()
    }
}
