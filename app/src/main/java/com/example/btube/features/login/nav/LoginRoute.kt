package com.example.btube.features.login.nav

import kotlinx.serialization.Serializable

/**
 * 登录模块内部路由
 */
@Serializable
sealed class LoginRoute {
    @Serializable
    data object QRCode : LoginRoute()

    @Serializable
    data object SMS : LoginRoute()

    @Serializable
    data object Password : LoginRoute()
}
