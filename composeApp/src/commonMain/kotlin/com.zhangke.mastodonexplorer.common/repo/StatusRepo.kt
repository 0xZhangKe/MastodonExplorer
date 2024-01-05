package com.zhangke.mastodonexplorer.common.repo

import com.zhangke.mastodonexplorer.common.entities.StatusEntity
import de.jensklingenberg.ktorfit.http.GET

interface MastodonApi {

    @GET("api/v1/trends/statuses")
    suspend fun getTrendsStatus(): List<StatusEntity>
}

class StatusRepo {

    private val mastodonApi = newKtorfit("https://androiddev.social/").create<MastodonApi>()

    suspend fun getTrendsStatus(): List<StatusEntity> {
        return mastodonApi.getTrendsStatus()
    }
}
