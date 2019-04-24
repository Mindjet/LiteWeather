package io.github.mindjet.liteweather.util

import android.app.Activity
import android.content.Intent
import android.view.View
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

infix fun View.turnTo(state: Int) {
    this.visibility = state
}

inline infix fun <reified T> Activity.goto(clazz: Class<T>) {
    this.startActivity(Intent(this, T::class.java))
}

//https://weatherapi.market.xiaomi.com/wtr-v3/weather/all?latitude=110&longitude=112&locationKey=weathercn%3A101010100&days=15&appKey=weather20151024&sign=zUFJoAR2ZVrDy1vF3D07&appVersion=87&isGlobal=false&modDevice=&locale=zh_cn