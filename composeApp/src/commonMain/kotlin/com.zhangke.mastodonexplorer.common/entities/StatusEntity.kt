package com.zhangke.mastodonexplorer.common.entities

import kotlinx.serialization.Serializable

@Serializable
data class StatusEntity(
    val id: String,
    val content: String,
    val created_at: String,
    val account: AccountEntity,
    val media_attachments: List<MastodonAttachment>,
)
