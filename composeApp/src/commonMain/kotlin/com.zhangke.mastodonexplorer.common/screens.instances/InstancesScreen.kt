package com.zhangke.mastodonexplorer.common.screens.instances

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import com.zhangke.mastodonexplorer.common.composable.LoadableLayout
import com.zhangke.mastodonexplorer.common.composable.Toolbar
import com.zhangke.mastodonexplorer.common.entities.MastodonInstanceEntity
import com.zhangke.mastodonexplorer.common.screens.feeds.FeedsScreen

class InstancesScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { InstancesViewModel() }
        val loadableState by screenModel.uiState.collectAsState()
        Scaffold(
            topBar = {
                Toolbar(
                    title = "Instances",
                )
            },
        ) { paddingValues ->
            LoadableLayout(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                state = loadableState,
                failed = { exception ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = exception.message.orEmpty(),
                            style = MaterialTheme.typography.subtitle1,
                        )

                        Button(
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = screenModel::onRetryClick,
                        ) {
                            Text(text = "Retry")
                        }
                    }
                }
            ) { uiState ->
                var columnCount by remember {
                    mutableIntStateOf(1)
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnCount),
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues)
                        .onGloballyPositioned {
                            val width = it.size.width
                            val height = it.size.height
                            if (width < height) {
                                columnCount = 2
                            } else {
                                columnCount = 4
                            }
                        },
                ) {
                    items(uiState.instances) { item ->
                        InstanceUi(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .aspectRatio(0.7F)
                                .clickable {
                                    navigator.push(FeedsScreen(item.domain))
                                },
                            instance = item,
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun InstanceUi(
        modifier: Modifier,
        instance: MastodonInstanceEntity,
    ) {
        Card(
            modifier = modifier,
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(1.6F)
                            .clip(RoundedCornerShape(6.dp)),
                        painter = rememberImagePainter(url = instance.proxied_thumbnail),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = instance.category.uppercase() + " | Users: " + instance.total_users,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = instance.domain,
                        style = MaterialTheme.typography.h6,
                    )

                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = instance.description,
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
