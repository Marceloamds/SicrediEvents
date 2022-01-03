package com.sicredi.events.presentation.util.extension

import android.content.Context
import com.sicredi.events.R
import java.util.*

private const val BRAZILIAN_WEEKDAY_SUFFIX = "-feira"

fun Double.toMoneyString(context: Context): String {
    return context.getString(R.string.money_template, this)
}

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun String.removeWeekdaySuffix(): String {
    return removeSuffix(BRAZILIAN_WEEKDAY_SUFFIX)
}