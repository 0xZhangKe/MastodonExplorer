package com.zhangke.mastodonexplorer.common.utils

expect class DateFormat() {

    fun formatToMedium(timestamp: Long): String
}
