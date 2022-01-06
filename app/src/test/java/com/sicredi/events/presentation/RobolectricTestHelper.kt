package com.sicredi.events.presentation

import android.app.Activity
import android.widget.TextView
import org.junit.Assert.*

fun TextView.assertText(expected: String?) {
    assertEquals(expected, this.text)
}

fun TextView.assertTextContains(expected: String?) {
    assert(this.text.contains(expected.toString()))
}

fun <T> Activity.getBinding(): T {
    return javaClass.getDeclaredField("binding").let { field ->
        field.isAccessible = true
        return@let field.get(this) as T
    }
}