package com.zhangke.mastodonexplorer.common.entities

import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment.Companion.TYPE_AUDIO
import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment.Companion.TYPE_GIFV
import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment.Companion.TYPE_IMAGE
import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment.Companion.TYPE_UNKNOWN
import com.zhangke.mastodonexplorer.common.entities.MastodonAttachment.Companion.TYPE_VIDEO
import kotlinx.serialization.Serializable

@Serializable
data class MastodonAttachment(
    val id: String,
    /**
     * Media type.
     * enum of [TYPE_UNKNOWN], [TYPE_IMAGE], [TYPE_GIFV], [TYPE_VIDEO], [TYPE_AUDIO]
     */
    val type: String,
    val url: String,
    val preview_url: String,
    val remote_url: String? = null,
    val description: String? = null,
    val blurhash: String? = null,
) {

    companion object {

        const val TYPE_UNKNOWN = "unknown"
        const val TYPE_IMAGE = "image"
        const val TYPE_GIFV = "gifv"
        const val TYPE_VIDEO = "video"
        const val TYPE_AUDIO = "audio"
    }
}
