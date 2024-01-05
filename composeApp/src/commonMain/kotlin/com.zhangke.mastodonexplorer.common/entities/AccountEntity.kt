package com.zhangke.mastodonexplorer.common.entities

import kotlinx.serialization.Serializable

@Serializable
data class AccountEntity (
    val id: String,
    val username: String,
    val acct: String,
    val display_name: String,
    val locked: Boolean,
    val created_at: String,
    val followers_count: Int,
    val following_count: Int,
    val statuses_count: Int,
    val note: String,
    val url: String,
    val avatar: String,
    val avatar_static: String,
    val header: String,
    val header_static: String,
)
