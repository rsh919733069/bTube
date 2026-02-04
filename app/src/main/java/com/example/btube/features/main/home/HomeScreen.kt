package com.example.btube.features.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.btube.features.main.HomeViewModel
import com.example.btube.features.main.home.anime.AnimationScreen
import com.example.btube.features.main.home.anime.BangumiScreen
import com.example.btube.features.main.home.hot.HotScreen
import com.example.btube.features.main.home.recommend.RecommendScreen
import com.example.btube.nav.AppRoute
import org.koin.androidx.compose.koinViewModel

/** 与 bilitube 对齐：推荐、热门、番剧、国创 */
private val HOME_TABS = listOf("推荐", "热门", "番剧", "国创")

@Composable
fun HomeScreen(
    navigateToAppRoute: (AppRoute) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val recommends = viewModel.recommends.collectAsLazyPagingItems()
    val hots = viewModel.hots.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp
        ) {
            HOME_TABS.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTab) {
            0 -> RecommendScreen(
                recommends = recommends,
                navigateToAppRoute = navigateToAppRoute,
                modifier = Modifier.fillMaxSize()
            )
            1 -> HotScreen(
                hotVideos = hots,
                navigateToAppRoute = navigateToAppRoute,
                modifier = Modifier.fillMaxSize()
            )
            2 -> BangumiScreen(modifier = Modifier.fillMaxSize())
            3 -> AnimationScreen(modifier = Modifier.fillMaxSize())
        }
    }
}
