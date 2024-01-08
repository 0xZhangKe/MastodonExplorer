package com.zhangke.mastodonexplorer.common.screens.feeds

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.zhangke.mastodonexplorer.common.composable.LoadableState
import com.zhangke.mastodonexplorer.common.repo.MastodonRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedsViewModel : ScreenModel {

    lateinit var instanceDomain: String

    private val _uiState: MutableStateFlow<LoadableState<FeedsUiState>> = MutableStateFlow(LoadableState.Idle())
    val uiState: StateFlow<LoadableState<FeedsUiState>> = _uiState

    private lateinit var mastodonRepo: MastodonRepo

    fun onPrepared() {
        mastodonRepo = MastodonRepo(instanceDomain.domainToBaseUrl())
        loadFeeds()
    }

    private fun loadFeeds() {
        if (_uiState.value.isLoading) return
        screenModelScope.launch {
            _uiState.value = LoadableState.loading()
            try {
                val feeds = mastodonRepo.getTrendsStatus()
                _uiState.value = LoadableState.success(FeedsUiState(feeds))
            } catch (e: Throwable) {
                _uiState.value = LoadableState.failed(e)
            }
        }
    }

    private fun String.domainToBaseUrl(): String {
        var fixedUrl = this
        if (!fixedUrl.lowercase().startsWith("http://") && !fixedUrl.lowercase().startsWith("https://")) {
            fixedUrl = "https://$fixedUrl"
        }
        if (!fixedUrl.endsWith("/")) {
            fixedUrl = "$fixedUrl/"
        }
        return fixedUrl
    }
}
