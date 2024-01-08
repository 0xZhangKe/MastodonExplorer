package com.zhangke.mastodonexplorer.common.repo

import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity
import com.zhangke.mastodonexplorer.common.network.newKtorfit
import de.jensklingenberg.ktorfit.http.GET

interface JoinMastodonApi {

    @GET("servers")
    suspend fun getAllInstance(): List<MastodonInstanceEntity>
}

object JoinMastodonRepo {

    private val api = newKtorfit("https://api.joinmastodon.org/").create<JoinMastodonApi>()

    suspend fun getAllInstance(): List<MastodonInstanceEntity> {
        return api.getAllInstance()
    }
}
