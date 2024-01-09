package com.zhangke.mastodonexplorer.common.repo

import com.zhangke.mastodonexplorer.common.db.MastodonLocalRepo
import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity
import com.zhangke.mastodonexplorer.common.network.newKtorfit
import de.jensklingenberg.ktorfit.http.GET
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface JoinMastodonApi {

    @GET("servers")
    suspend fun getAllInstance(): List<MastodonInstanceEntity>
}

object JoinMastodonRepo {

    private val api = newKtorfit("https://api.joinmastodon.org/").create<JoinMastodonApi>()

    private val mastodonLocalRepo = MastodonLocalRepo()

    suspend fun getAllInstance(): List<MastodonInstanceEntity> = withContext(Dispatchers.IO) {
        val listInDB = mastodonLocalRepo.queryAll()
        println("get all instance from database count is ${listInDB.size}")
        if (listInDB.isNotEmpty()) return@withContext listInDB
        val instancesFromServer = api.getAllInstance()
        println("get all instance from server count is ${instancesFromServer.size}")
        mastodonLocalRepo.insert(instancesFromServer)
        return@withContext instancesFromServer
    }
}
