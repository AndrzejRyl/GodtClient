package com.fleenmobile.androidinterviewtask

import android.app.Activity
import android.view.View
import android.widget.Toast

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Activity.showToast(
        message: String,
        lenght: Int
) = Toast.makeText(this, message, lenght).show()