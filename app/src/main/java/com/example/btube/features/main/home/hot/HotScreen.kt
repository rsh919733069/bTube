package com.example.btube.features.main.home.hot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.btube.bili_sdk.model_v2.hot.HotItem
import com.example.btube.features.main.home.component.VerticalVideoCard
import com.example.btube.model.play.MediaPlayConfig
import com.example.btube.nav.AppRoute
import com.example.btube.ui.viewmodel.SharedViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotScreen(
    hotVideos: LazyPagingItems<HotItem>,
    navigateToAppRoute: (AppRoute) -> Unit,
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = koinInject()
) {
    val refreshState = rememberPullToRefreshState()
    val isRefreshing = hotVideos.loadState.refresh is LoadState.Loading

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = refreshState,
        onRefresh = { hotVideos.refresh() },
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(count = hotVideos.itemCount, key = { hotVideos.peek(it)?.bvid ?: it }) { index ->
                val item = hotVideos[index]
                if (item != null) {
                    VerticalVideoCard(
                        pic = item.pic.ifEmpty { item.cover43 },
                        title = item.title,
                        ownerName = item.owner.name,
                        viewCount = formatCount(item.stat.view.toLong()),
                        duration = formatDuration(item.duration.toLong()),
                        onClick = {
                            sharedViewModel.setPlayParam(
                                MediaPlayConfig.BasicVideoConfig(
                                    bvid = item.bvid,
                                    aid = item.aid,
                                    cid = item.cid
                                )
                            )
                            navigateToAppRoute(AppRoute.Play)
                        }
                    )
                }
            }
            if (hotVideos.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private fun formatCount(count: Long): String {
    return when {
        count >= 1_000_000_000 -> "${count / 1_000_000_000}亿"
        count >= 10_000 -> "${count / 10_000}万"
        count >= 1_000 -> "${count / 1_000}千"
        else -> count.toString()
    }
}

private fun formatDuration(seconds: Long): String {
    val m = seconds / 60
    val s = seconds % 60
    return "%d:%02d".format(m, s)
}
