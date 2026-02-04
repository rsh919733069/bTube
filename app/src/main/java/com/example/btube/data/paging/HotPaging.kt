package com.example.btube.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.btube.bili_sdk.apis.VideoApi
import com.example.btube.bili_sdk.model_v2.hot.HotItem
import com.example.btube.core.COOKIE_KEY
import com.example.btube.data.local.datastore.dataStore
import kotlinx.coroutines.flow.firstOrNull

class HotPaging(
    private val videoApi: VideoApi,
    private val context: Context
) : PagingSource<Int, HotItem>() {

    override fun getRefreshKey(state: PagingState<Int, HotItem>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HotItem> {
        return try {
            val page = params.key ?: 1
            val cookie = context.dataStore.data.firstOrNull()?.get(COOKIE_KEY)

            val response = videoApi.getHots(
                cookie = cookie,
                pn = page,
                ps = 20
            )

            val data = response.data.list

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
