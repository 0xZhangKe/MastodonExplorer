package com.zhangke.mastodonexplorer.common.screens.instances

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.zhangke.mastodonexplorer.common.composable.LoadableState
import com.zhangke.mastodonexplorer.common.repo.JoinMastodonRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InstancesViewModel : ScreenModel {

    private val _uiState = MutableStateFlow<LoadableState<InstancesUiState>>(LoadableState.Idle())
    val uiState: StateFlow<LoadableState<InstancesUiState>> = _uiState

    init {
        loadInstanceList()
    }

    fun onRetryClick() {
        loadInstanceList()
    }

    private fun loadInstanceList() {
        if (_uiState.value.isLoading) return
        screenModelScope.launch {
            _uiState.value = LoadableState.Loading()
            val instances = try {
                JoinMastodonRepo.getAllInstance()
            } catch (e: Exception) {
                _uiState.value = LoadableState.Failed(e)
                return@launch
            }
            _uiState.value = LoadableState.Success(InstancesUiState(instances))
        }
    }
}
