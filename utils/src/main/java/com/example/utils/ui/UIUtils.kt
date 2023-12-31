package com.example.utils.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.utils.R

fun getStubAlertDialog(context: Context) : AlertDialog {
    return getAlertDialog(context, null, null)
}

fun getAlertDialog(context: Context, title: String?, message: String?) :AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.dialog_title_stub)
    if(!title.isNullOrEmpty()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)

    if (!message.isNullOrEmpty()){
        builder.setMessage(message)
    }

    builder.setCancelable(true)
    builder.setPositiveButton(R.string.dialog_button_cancel) {
            dialog, _ -> dialog.dismiss()
    }
    return builder.create()
}