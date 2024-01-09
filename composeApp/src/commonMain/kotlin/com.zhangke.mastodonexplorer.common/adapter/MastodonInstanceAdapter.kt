package com.zhangke.mastodonexplorer.common.adapter

import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity
import comzhangkemastodonexplorer.Instances

class MastodonInstanceAdapter {

    fun toInstanceEntity(instances: Instances) = MastodonInstanceEntity(
        category = instances.category,
        description = instances.description,
        domain = instances.domain,
        language = instances.language,
        last_week_users = instances.last_week_users.toInt(),
        proxied_thumbnail = instances.proxied_thumbnail,
        region = instances.region,
        total_users = instances.total_users.toInt(),
    )

    fun toInstance(entity: MastodonInstanceEntity) = Instances(
        category = entity.category,
        description = entity.description,
        domain = entity.domain,
        language = entity.language,
        last_week_users = entity.last_week_users.toLong(),
        proxied_thumbnail = entity.proxied_thumbnail,
        region = entity.region,
        total_users = entity.total_users.toLong(),
    )
}
