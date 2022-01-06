package com.sicredi.events.presentation

import android.app.Activity
import android.text.Spanned
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.AttributeSetBuilder

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