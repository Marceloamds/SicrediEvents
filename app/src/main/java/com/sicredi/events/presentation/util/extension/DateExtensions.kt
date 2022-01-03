package com.sicredi.events.presentation.util.extension

import android.content.Context
import com.sicredi.events.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

private const val DAY_MONTH_YEAR = "dd/MM/yyyy"

fun Date.format(pattern: String = DAY_MONTH_YEAR): String {
    return getSimpleDateFormatter(pattern).format(this)
}

fun getSimpleDateFormatter(
    pattern: String,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): SimpleDateFormat {
    return SimpleDateFormat(pattern, Locale.getDefault()).also {
        it.timeZone = timeZone
    }
}

fun LocalDate.getDayDescription(context: Context): String{
    return context.getString(
        R.string.date_description_template,
        dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).capitalizeFirstLetter().removeWeekdaySuffix(),
        dayOfMonth,
        month.getDisplayName(TextStyle.FULL, Locale.getDefault()).capitalizeFirstLetter()
    )
}
