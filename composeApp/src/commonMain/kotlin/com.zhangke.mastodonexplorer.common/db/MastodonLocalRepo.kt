package com.zhangke.mastodonexplorer.common.db

import com.zhangke.mastodonexplorer.common.adapter.MastodonInstanceAdapter
import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity
import comzhangkemastodonexplorer.InstancesQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class MastodonLocalRepo {

    private val instancesQueries: InstancesQueries by lazy {
        createDatabase(DriverFactory()).instancesQueries
    }

    private val mastodonInstanceAdapter = MastodonInstanceAdapter()

    suspend fun queryAll(): List<MastodonInstanceEntity> = withContext(Dispatchers.IO) {
        return@withContext instancesQueries.selectAll()
            .executeAsList()
            .map(mastodonInstanceAdapter::toInstanceEntity)
    }

    suspend fun insert(instances: List<MastodonInstanceEntity>) = withContext(Dispatchers.IO) {
        instancesQueries.transaction {
            instances.forEach { instance ->
                instancesQueries.insert(
                    domain = instance.domain,
                    category = instance.category,
                    description = instance.description,
                    language = instance.language,
                    last_week_users = instance.last_week_users.toLong(),
                    proxied_thumbnail = instance.proxied_thumbnail,
                    region = instance.region,
                    total_users = instance.total_users.toLong(),
                )
            }
        }
    }
}
