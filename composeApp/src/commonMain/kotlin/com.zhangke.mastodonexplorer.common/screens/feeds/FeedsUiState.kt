package com.zhangke.mastodonexplorer.common.screens.feeds

import com.zhangke.mastodonexplorer.common.entities.StatusEntity

data class FeedsUiState(
    val loading: Boolean,
    val feeds: List<StatusEntity>,
    val errorMessage: String? = null,
)
