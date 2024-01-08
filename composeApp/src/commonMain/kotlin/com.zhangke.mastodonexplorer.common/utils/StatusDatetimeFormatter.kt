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

    fun format(config: DatetimeFormatConfig, datetime: String): String {
        val instant = try {
            datetime.toInstant()
        } catch (e: Throwable) {
            return datetime
        }
        val duration = Clock.System.now().minus(instant)
        val inWholeDays = duration.inWholeDays
        if (inWholeDays > 3) {
            return DateFormat().formatToMedium(instant.toEpochMilliseconds())
        }
        return formatDuration(config, duration)
    }

    private fun formatDuration(config: DatetimeFormatConfig, duration: Duration): String {
        if (duration.isInfinite()) return ""
        var leftDuration = duration
        val day = (leftDuration).inWholeDays.days
        leftDuration -= day
        if (day > 0.days) {
            return "${day.toInt(DurationUnit.DAYS)} ${config.day}"
        }
        val hours = leftDuration.inWholeHours.hours
        leftDuration -= hours
        if (hours > 0.hours) {
            return "${hours.toInt(DurationUnit.HOURS)} ${config.hour}"
        }
        val minutes = leftDuration.inWholeMinutes.minutes
        leftDuration -= minutes
        if (minutes > 0.minutes) {
            return "${minutes.toInt(DurationUnit.MINUTES)} ${config.minutes}"
        }
        val seconds = leftDuration.inWholeSeconds.seconds
        return "${seconds.toInt(DurationUnit.SECONDS)} ${config.second}"
    }

    private fun convertFullMonthNamesToAbbreviations(text: String): String {
        val monthMap = mapOf(
            "January" to "Jan",
            "February" to "Feb",
            "March" to "Mar",
            "April" to "Apr",
            "May" to "May",
            "June" to "Jun",
            "July" to "Jul",
            "August" to "Aug",
            "September" to "Sep",
            "October" to "Oct",
            "November" to "Nov",
            "December" to "Dec"
        )
        var updatedText = text
        monthMap.forEach { (full, abbr) ->
            updatedText = updatedText.replace(full, abbr)
        }
        return updatedText
    }
}

data class DatetimeFormatConfig(
    val day: String,
    val hour: String,
    val minutes: String,
    val second: String,
)

fun defaultFormatConfig() = DatetimeFormatConfig(
    day = "day",
    hour = "hour",
    minutes = "min",
    second = "sec",
)
