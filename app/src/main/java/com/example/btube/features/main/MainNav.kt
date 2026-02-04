package com.example.btube.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Subscriptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btube.features.main.home.HomeScreen
import com.example.btube.features.main.navigation.MainRoute
import com.example.btube.features.main.profile.ProfileScreen
import com.example.btube.features.main.subscription.SubscriptionScreen
import com.example.btube.nav.AppRoute

@Composable
fun MainNav(
    onNavigate: (AppRoute) -> Unit
) {
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()

    // 与 bilitube 对齐：底部仅 首页、订阅、我的 三项
    val navItems = listOf(
        MainRoute.Home to "首页",
        MainRoute.Subscription to "订阅",
        MainRoute.Profile to "我的"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 0.dp
            ) {
                navItems.forEach { (route, label) ->
                    val selected = currentDestination?.destination?.hasRoute(route::class) == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                        when {
                            selected -> {}
                            else -> {
                                navController.navigate(route) {
                                    popUpTo(MainRoute.Home) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        },
                        icon = {
                            Icon(
                                imageVector = when (route) {
                                    MainRoute.Home -> Icons.Rounded.Home
                                    MainRoute.Subscription -> Icons.Rounded.Subscriptions
                                    MainRoute.Profile -> Icons.Rounded.Person
                                    else -> Icons.Rounded.Home
                                },
                                contentDescription = label
                            )
                        },
                        label = { Text(label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        },
        content = { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainRoute.Home,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<MainRoute.Home> {
                HomeScreen(
                    navigateToAppRoute = onNavigate
                )
            }
            composable<MainRoute.Subscription> {
                SubscriptionScreen(
                    navigateToAppRoute = onNavigate
                )
            }
            composable<MainRoute.Profile> {
                ProfileScreen(
                    navigateToAppRoute = onNavigate
                )
            }
        }
    })
}
