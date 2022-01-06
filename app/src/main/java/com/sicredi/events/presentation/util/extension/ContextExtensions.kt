package com.sicredi.events.presentation.util.extension

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import com.sicredi.events.R
import com.sicredi.events.presentation.util.dialog.DialogData
import com.sicredi.events.presentation.util.navigation.NavData

const val INTENT_TEXT_TYPE = "text/plain"

fun Context.showDialog(dialogData: DialogData): Dialog {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(dialogData.title)
    builder.setMessage(dialogData.message)
    if (dialogData.confirmButtonText == null && dialogData.onConfirm == null) {
        builder.setPositiveButton(dialogData.cancelButtonText, dialogData.onCancel)
    } else {
        builder.setPositiveButton(
            dialogData.confirmButtonText, dialogData.onConfirm
                ?: dialogData.onCancel
        )
        if (dialogData.cancelButtonText != null || dialogData.onCancel != null) {
            builder.setNegativeButton(dialogData.cancelButtonText, dialogData.onCancel)
        }
    }
    builder.setCancelable(dialogData.cancelable ?: true)
    return builder.show()
}

fun AlertDialog.Builder.setPositiveButton(buttonText: String?, onClick: (() -> Unit)?) =
    setPositiveButton(
        buttonText ?: context.getString(R.string.global_ok),
        onClick?.let { { _: DialogInterface, _: Int -> it() } }
    )

fun AlertDialog.Builder.setNegativeButton(buttonText: String?, onClick: (() -> Unit)?) =
    setNegativeButton(
        buttonText ?: context.getString(R.string.global_cancel),
        onClick?.let { { _: DialogInterface, _: Int -> it() } }
    )

fun Activity.onGoTo(navData: NavData?) {
    navData?.navigate(this)
}

fun Activity.onDialog(dialogData: DialogData?) {
    dialogData?.let(::showDialog)
}

fun Context.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
