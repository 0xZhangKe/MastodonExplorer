package com.zhangke.mastodonexplorer.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import com.zhangke.mastodonexplorer.common.entities.AccountEntity
import com.zhangke.mastodonexplorer.common.utils.StatusDatetimeFormatter
import com.zhangke.mastodonexplorer.common.utils.defaultFormatConfig

@Composable
fun StatusInfoLine(
    modifier: Modifier,
    blogAuthor: AccountEntity,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BlogAuthorAvatar(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            imageUrl = blogAuthor.avatar,
        )
        Column(modifier = Modifier.padding(start = 8.dp).weight(1F)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = blogAuthor.display_name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Box(Modifier.weight(1F))
                Text(
                    text = StatusDatetimeFormatter.format(defaultFormatConfig(), blogAuthor.created_at),
                    style = MaterialTheme.typography.body2,
                )
            }
            Text(
                text = blogAuthor.acct,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun BlogAuthorAvatar(
    modifier: Modifier,
    imageUrl: String?,
) {
    val painter = if (imageUrl.isNullOrEmpty()) {
        rememberVectorPainter(Icons.Default.AccountCircle)
    } else {
        rememberImagePainter(imageUrl)
    }
    Image(
        modifier = modifier
            .clip(CircleShape),
        painter = painter,
        contentDescription = null,
    )
}
