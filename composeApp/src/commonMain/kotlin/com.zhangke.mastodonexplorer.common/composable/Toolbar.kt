package com.zhangke.mastodonexplorer.common.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val navigationIcon: (@Composable (() -> Unit)) = if (onBackClick != null) {
        @Composable
        {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.ArrowBack),
                    "back"
                )
            }
        }
    } else {
        {}
    }
    TopAppBar(
        navigationIcon = navigationIcon,
        actions = actions,
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
            )
        },
    )
}