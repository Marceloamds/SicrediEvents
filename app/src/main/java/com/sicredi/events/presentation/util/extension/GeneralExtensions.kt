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

fun <T1, T2, R> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun tryCatch(onFailure : (Throwable) -> Unit, block: () -> Unit) {
    try {
        block()
    }
    catch (throwable: Throwable) {
        onFailure(throwable)
    }
}