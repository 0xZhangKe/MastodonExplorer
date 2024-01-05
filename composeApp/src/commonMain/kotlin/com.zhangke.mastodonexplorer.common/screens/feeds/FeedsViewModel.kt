package com.zhangke.mastodonexplorer.common.screens.feeds

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.zhangke.mastodonexplorer.common.repo.StatusRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedsViewModel : ScreenModel {

    private val _uiState = MutableStateFlow(FeedsUiState(false, emptyList()))
    val uiState: StateFlow<FeedsUiState> = _uiState

    init {
        loadFeeds()
    }

    private fun loadFeeds() {
        screenModelScope.launch {
            _uiState.update {
                it.copy(loading = true)
            }
            try {
                val feeds = StatusRepo().getTrendsStatus()
                _uiState.update {
                    it.copy(loading = false, feeds = feeds)
                }
            } catch (e: Throwable) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = e.message,
                    )
                }
            }
        }
    }

    fun onErrorMessageDismiss() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}
