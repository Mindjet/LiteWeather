package io.github.mindjet.liteweather.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun String.conditionColor(): Int {
    return ConditionHelper.getConditionColor(this)
}

fun ImageView.load(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}