package com.example.btube.bili_sdk.apis

import com.example.btube.bili_sdk.model_v2.common.BiliResponse
import com.example.btube.bili_sdk.model_v2.hot.HotModel
import com.example.btube.bili_sdk.model_v2.recommend.RecommendModel

/**
 * 视频相关 API
 */
interface VideoApi {
    suspend fun getRecommends(
        cookie: String? = null,
        refreshType: Int = 4,
        lastShowList: String? = null,
        feedVersion: String = "V8",
        freshIdx: Int = 1,
        freshIdx1h: Int = 1,
        brush: Int = 1,
        webLocation: Int = 1430650,
        homepageVer: Int = 1,
        fetchRow: Int = 1,
        ps: Int = 12,
        yNum: Int = 3
    ): BiliResponse<RecommendModel>

    suspend fun getHots(
        cookie: String? = null,
        pn: Int = 1,
        ps: Int = 20
    ): BiliResponse<HotModel>
}
