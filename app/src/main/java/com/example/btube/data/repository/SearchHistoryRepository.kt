package com.example.btube.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.btube.data.local.room.dao.SearchHistoryDao
import com.example.btube.data.local.room.entity.SearchHistory
import kotlinx.coroutines.flow.Flow

/**
 * 搜索历史 Repository
 */
class SearchHistoryRepository(
    private val searchHistoryDao: SearchHistoryDao
) {

    fun searchHistoryPager(): Flow<PagingData<SearchHistory>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { searchHistoryDao.getHistoryPaging() }
    ).flow

    suspend fun addSearchHistory(keyword: String) {
        val exist = searchHistoryDao.findByKeyword(keyword)
        if (exist != null) {
            searchHistoryDao.update(exist.copy(timestamp = System.currentTimeMillis()))
        } else {
            searchHistoryDao.insert(SearchHistory(keyword = keyword))
        }
    }

    suspend fun deleteSearchHistory(keyword: String) {
        searchHistoryDao.delete(keyword)
    }

    suspend fun clearAll() {
        searchHistoryDao.clearAll()
    }
}
