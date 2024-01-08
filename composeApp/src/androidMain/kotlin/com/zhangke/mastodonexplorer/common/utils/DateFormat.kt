package com.zhangke.mastodonexplorer.common.utils

import java.text.DateFormat
import java.util.Date
import java.util.Locale

actual class DateFormat {
    actual fun formatToMedium(timestamp: Long): String {
        val format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return format.format(Date(timestamp))
    }
}
