package com.example.btube.nav

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btube.AppState
import com.example.btube.features.login.LoginNav
import com.example.btube.features.main.MainNav
import com.example.btube.features.setting.SettingPlaceholder
import com.example.btube.features.splash.SplashScreen
import com.example.btube.ui.viewmodel.SharedViewModel

/**
 * 应用主导航
 * 配置所有顶级路由及转场动画
 */
@Composable
fun AppNav(
    sharedViewModel: SharedViewModel,
    appNavController: NavHostController,
    appState: AppState
) {
    var localIsLogin by remember { mutableStateOf(false) }

    LaunchedEffect(appState.isLogin) {
        localIsLogin = appState.isLogin
    }

    NavHost(
        navController = appNavController,
        startDestination = AppRoute.Splash,
        modifier = Modifier.fillMaxSize(),
        enterTransition = {
            when {
                isSplashOrLoginToMainNav(initialState, targetState) -> fadeIn()
                else -> slideInHorizontally { it }
            }
        },
        exitTransition = {
            when {
                isSplashOrLoginToMainNav(initialState, targetState) -> fadeOut()
                else -> slideOutHorizontally { -it }
            }
        },
        popEnterTransition = {
            when {
                isSplashOrLoginToMainNav(initialState, targetState) -> fadeIn()
                else -> slideInHorizontally { -it }
            }
        },
        popExitTransition = {
            when {
                isSplashOrLoginToMainNav(initialState, targetState) -> fadeOut()
                else -> slideOutHorizontally { it }
            }
        }
    ) {
        composable<AppRoute.Splash> {
            SplashScreen {
                val nextRoute = when {
                    localIsLogin -> AppRoute.MainNav
                    else -> AppRoute.LoginNav
                }
                appNavController.navigate(nextRoute) {
                    popUpTo<AppRoute.Splash> { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable<AppRoute.LoginNav> {
            LoginNav(
                onNavigateToMain = {
                    appNavController.navigate(AppRoute.MainNav) {
                        popUpTo<AppRoute.LoginNav> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<AppRoute.MainNav> {
            MainNav(
                onNavigate = { appNavController.navigate(it) }
            )
        }

        // 以下路由占位，后续阶段实现具体页面
        composable<AppRoute.SettingNav> {
            SettingPlaceholder(
                onNavigateUp = { appNavController.navigateUp() },
                onNavigate = { appNavController.navigate(it) }
            )
        }
    }
}

private fun isSplashOrLoginToMainNav(
    initialState: NavBackStackEntry,
    targetState: NavBackStackEntry,
): Boolean {
    val isSplashToMainNav =
        initialState.destination.hasRoute(AppRoute.Splash::class) &&
            targetState.destination.hasRoute(AppRoute.MainNav::class)
    val isLoginNavToMainNav =
        initialState.destination.hasRoute(AppRoute.LoginNav::class) &&
            targetState.destination.hasRoute(AppRoute.MainNav::class)
    return isLoginNavToMainNav || isSplashToMainNav
}
