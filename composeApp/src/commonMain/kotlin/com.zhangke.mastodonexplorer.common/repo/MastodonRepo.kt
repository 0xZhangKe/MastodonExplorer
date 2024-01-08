package com.zhangke.mastodonexplorer.common.repo

import com.zhangke.mastodonexplorer.common.entities.StatusEntity
import com.zhangke.mastodonexplorer.common.network.newKtorfit
import de.jensklingenberg.ktorfit.http.GET

interface MastodonApi {

    @GET("api/v1/trends/statuses")
    suspend fun getTrendsStatus(): List<StatusEntity>
}

class MastodonRepo(baseUrl: String) {

    private val mastodonApi = newKtorfit(baseUrl).create<MastodonApi>()

    suspend fun getTrendsStatus(): List<StatusEntity> {
        return mastodonApi.getTrendsStatus()
    }
}
