package com.example.btube.features.main.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoute {
    @Serializable
    data object Home : MainRoute()

    @Serializable
    data object Profile : MainRoute()

    @Serializable
    data object Subscription : MainRoute()

    @Serializable
    data object Settings : MainRoute()
}
