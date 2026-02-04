package com.example.btube

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.OptIn
import androidx.datastore.preferences.core.edit
import androidx.media3.common.util.UnstableApi
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.example.btube.bili_sdk.wbi.GetWbi
import com.example.btube.bili_sdk.wbi.WbiParams
import com.example.btube.core.COOKIE_KEY
import com.example.btube.core.IMG_URL_KEY
import com.example.btube.core.SUB_URL_KEY
import com.example.btube.data.local.datastore.dataStore
import com.example.btube.di.appModule
import com.example.btube.di.dataModule
import com.example.btube.di.roomModule
import com.example.btube.di.viewModelModule
import com.example.btube.network.HttpClientFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.conscrypt.Conscrypt
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.security.Security

/**
 * bTube应用类
 */
class BTubeApp : Application(), SingletonImageLoader.Factory {
    companion object {
        private val TAG = BTubeApp::class.simpleName
    }

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        
        // 初始化Conscrypt安全提供者（仅Android 12以下需要）
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            Security.insertProviderAt(Conscrypt.newProvider(), 1)
        }
        
        // 初始化Koin依赖注入
        startKoin {
            androidContext(this@BTubeApp)
            modules(appModule, roomModule, dataModule, viewModelModule)
        }
        
        // 初始化WBI签名（异步）
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            initWbiKeys()
        }
        
        Log.d(TAG, "bTube应用初始化完成")
    }

    /**
     * 初始化WBI签名密钥
     * 优先从缓存读取，缓存不存在则从网络获取
     */
    private suspend fun initWbiKeys() {
        try {
            val settings = dataStore.data.firstOrNull()
            val imgKey = settings?.get(IMG_URL_KEY)
            val subKey = settings?.get(SUB_URL_KEY)

            if (imgKey != null && subKey != null) {
                WbiParams.initWbi(imgKey, subKey)
                Log.d(TAG, "WBI密钥从缓存加载成功")
            } else {
                val cookie = settings?.get(COOKIE_KEY)
                GetWbi.getWbiRequest(HttpClientFactory.client).wbi(cookie) { biliWbi ->
                    dataStore.edit { prefs ->
                        prefs[IMG_URL_KEY] = biliWbi.wbiImg.imgUrl
                        prefs[SUB_URL_KEY] = biliWbi.wbiImg.subUrl
                    }
                    WbiParams.initWbi(biliWbi.wbiImg.imgUrl, biliWbi.wbiImg.subUrl)
                    Log.d(TAG, "WBI密钥从网络获取成功")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "初始化WBI密钥失败", e)
        }
    }

    /**
     * 创建Coil ImageLoader
     */
    @OptIn(UnstableApi::class)
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(this, 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.15)
                    .build()
            }
            .components {
                add(KtorNetworkFetcherFactory(httpClient = { HttpClientFactory.coilClient }))
            }
            .build()
    }
}
