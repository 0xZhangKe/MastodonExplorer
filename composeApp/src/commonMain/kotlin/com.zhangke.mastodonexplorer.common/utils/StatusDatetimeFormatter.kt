package com.zhangke.mastodonexplorer.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

object StatusDatetimeFormatter {

    fun format(datetime: String): String {
        val instant = try {
            datetime.toInstant()
        } catch (e: Throwable) {
            return datetime
        }
        val leftInstant = Clock.System.now().minus(instant)
        return formatDuration(leftInstant)
    }

    private fun formatDuration(duration: Duration): String {
        val builder = StringBuilder()
        if (duration.isInfinite()) return ""
        val month = duration.inWholeDays / 30
        if (month > 0) {
            builder.append(month)
            builder.append("mo ")
        }
        val day = (duration - (month * 30).days).inWholeDays.days
        if (day > 0.days) {
            builder.append(day.toInt(DurationUnit.DAYS))
            builder.append("d ")
        }
        val hours = (duration - day).inWholeHours.hours
        if (hours > 0.hours) {
            builder.append(hours.toInt(DurationUnit.HOURS))
            builder.append("h ")
        }
        val minutes = (duration - day - hours).inWholeMinutes.minutes
        if (minutes > 0.minutes) {
            builder.append(minutes.toInt(DurationUnit.MINUTES))
            builder.append("m ")
        }
        val seconds = (duration - day - hours - minutes).inWholeSeconds.seconds
        if (seconds > 0.seconds) {
            builder.append(seconds.toInt(DurationUnit.SECONDS))
            builder.append("s ")
        }
        builder.append("ago")
        return builder.toString()
    }
}
