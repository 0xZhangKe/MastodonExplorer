package com.zhangke.mastodonexplorer.common.screens.feeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.zhangke.mastodonexplorer.common.composable.LoadableLayout
import com.zhangke.mastodonexplorer.common.composable.MastodonAttachmentListUi
import com.zhangke.mastodonexplorer.common.composable.StatusInfoLine
import com.zhangke.mastodonexplorer.common.composable.Toolbar
import com.zhangke.mastodonexplorer.common.entities.StatusEntity

class FeedsScreen(private val instanceDomain: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { FeedsViewModel() }
        screenModel.instanceDomain = instanceDomain
        LaunchedEffect(instanceDomain) {
            screenModel.onPrepared()
        }
        val loadableUiState by screenModel.uiState.collectAsState()
        Scaffold(
            topBar = {
                Toolbar(
                    title = "Feeds",
                    onBackClick = navigator::pop,
                )
            },
        ) { paddingValues ->
            LoadableLayout(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                state = loadableUiState,
            ) { uiState ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.feeds) { item ->
                        StatusUi(
                            modifier = Modifier.fillMaxWidth(),
                            status = item,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatusUi(
    modifier: Modifier,
    status: StatusEntity,
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            StatusInfoLine(
                modifier = Modifier.fillMaxWidth(),
                blogAuthor = status.account,
            )
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = status.content,
                )
            }

            MastodonAttachmentListUi(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                attachments = status.media_attachments,
            )
            Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
        }
    }
}
