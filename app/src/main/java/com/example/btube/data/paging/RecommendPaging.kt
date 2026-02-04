package com.example.btube.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.btube.bili_sdk.apis.VideoApi
import com.example.btube.bili_sdk.model_v2.recommend.RecommendItem
import androidx.datastore.preferences.core.edit
import com.example.btube.core.COOKIE_KEY
import com.example.btube.core.LAST_SHOW_LIST_KEY
import com.example.btube.data.local.datastore.dataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlin.random.Random

class RecommendPaging(
    private val videoApi: VideoApi,
    private val context: Context
) : PagingSource<Int, RecommendItem>() {

    private var lastShowList: String? = null

    override fun getRefreshKey(state: PagingState<Int, RecommendItem>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendItem> {
        return try {
            val page = params.key ?: 1
            val cookie = context.dataStore.data.firstOrNull()?.get(COOKIE_KEY)
            if (page == 1) {
                lastShowList = context.dataStore.data.firstOrNull()?.get(LAST_SHOW_LIST_KEY)
                    ?.takeIf { it.isNotBlank() }
            }

            val response = videoApi.getRecommends(
                cookie = cookie,
                refreshType = Random.nextInt(3, 12),
                lastShowList = lastShowList,
                freshIdx = page,
                freshIdx1h = page,
                brush = page,
                fetchRow = (page - 1) * 3 + 1,
                ps = 12
            )

            val data = response.data.item.filter { it.owner != null && it.stat != null }

            lastShowList = response.data.item.joinToString(",") { "${it.goto}_${it.id}" }
            context.dataStore.edit { settings ->
                settings[LAST_SHOW_LIST_KEY] = lastShowList ?: ""
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
