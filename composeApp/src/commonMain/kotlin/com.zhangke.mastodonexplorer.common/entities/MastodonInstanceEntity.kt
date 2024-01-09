package com.zhangke.mastodonexplorer.common.entities

import kotlinx.serialization.Serializable

@Serializable
data class MastodonInstanceEntity(
    val category: String,
    val description: String,
    val domain: String,
    val language: String,
    val last_week_users: Int,
    val proxied_thumbnail: String,
    val region: String,
    val total_users: Int,
)
