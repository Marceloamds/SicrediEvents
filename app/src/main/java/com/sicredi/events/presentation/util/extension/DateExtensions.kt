package com.sicredi.events.presentation.util.extension

import android.content.Context
import com.sicredi.events.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

fun LocalDate.getDayDescription(context: Context): String {
    return context.getString(
        R.string.date_description_template,
        dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).capitalizeFirstLetter()
            .removeWeekdaySuffix(),
        dayOfMonth,
        month.getDisplayName(TextStyle.FULL, Locale.getDefault()).capitalizeFirstLetter()
    )
}
