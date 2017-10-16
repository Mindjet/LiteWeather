package io.github.mindjet.liteweather.helper

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by Mindjet on 2017/10/16.
 */

var toast: Toast? = null

fun show(context: Context, string: String) {
    checkToast(context, string)
}

fun show(context: Context, @StringRes stringRes: Int) {
    checkToast(context, context.resources.getString(stringRes))
}

private fun checkToast(context: Context, string: String) {
    if (toast == null) {
        toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)
    } else {
        toast?.setText(string)
    }
    toast?.show()
}