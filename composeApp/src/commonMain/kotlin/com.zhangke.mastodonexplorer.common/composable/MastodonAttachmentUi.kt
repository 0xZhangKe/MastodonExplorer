package com.zhangke.mastodonexplorer.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment

@Composable
fun MastodonAttachmentListUi(
    modifier: Modifier,
    attachments: List<MastodonAttachment>,
) {
    val imageAttachments = attachments.filter { it.type == MastodonAttachment.TYPE_IMAGE }
        .take(4)
    if (imageAttachments.isEmpty()) return
    Box(modifier = modifier) {
        when (imageAttachments.size) {
            1, 2, 3 -> {
                RowedImage(images = imageAttachments)
            }

            4 -> Column(modifier = Modifier.fillMaxWidth()) {
                RowedImage(images = imageAttachments.take(2))
                Box(Modifier.width(1.dp).height(8.dp))
                RowedImage(images = imageAttachments.takeLast(2))
            }
        }
    }
}

@Composable
private fun RowedImage(
    modifier: Modifier = Modifier,
    images: List<MastodonAttachment>,
) {
    Row(modifier = modifier.fillMaxWidth().aspectRatio(1.7F)) {
        images.forEachIndexed { index, attachment ->
            MastodonAttachmentUi(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                attachment = attachment,
            )
            if (index != images.lastIndex) {
                Box(Modifier.height(1.dp).width(8.dp))
            }
        }
    }
}

@Composable
fun MastodonAttachmentUi(
    modifier: Modifier,
    attachment: MastodonAttachment,
) {
    if (attachment.type != MastodonAttachment.TYPE_IMAGE) return
    val painter = rememberImagePainter(attachment.url)
    Image(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp)),
        painter = painter,
        contentDescription = null,
    )
}
