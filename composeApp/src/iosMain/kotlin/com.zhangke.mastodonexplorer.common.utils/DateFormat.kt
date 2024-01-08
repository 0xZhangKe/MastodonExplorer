package com.zhangke.mastodonexplorer.common.utils

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterNoStyle
import platform.Foundation.dateWithTimeIntervalSince1970

actual class DateFormat {

    actual fun formatToMedium(timestamp: Long): String {
        val date = NSDate.dateWithTimeIntervalSince1970(timestamp / 1000.0)
        val dateFormatter = NSDateFormatter()
        dateFormatter.setDateStyle(NSDateFormatterMediumStyle)
        dateFormatter.setTimeStyle(NSDateFormatterNoStyle)
        return dateFormatter.stringFromDate(date)
    }
}
