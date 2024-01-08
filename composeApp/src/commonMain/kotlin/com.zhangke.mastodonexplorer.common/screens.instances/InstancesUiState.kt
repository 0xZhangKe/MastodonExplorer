package com.zhangke.mastodonexplorer.common.screens.instances

import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity

data class InstancesUiState(
    val instances: List<MastodonInstanceEntity>,
)
