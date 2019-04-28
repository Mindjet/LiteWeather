package io.github.mindjet.liteweather.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide

val handler = Handler()

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

fun Activity.giveupFocus() {
    val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

fun Any.runDelay(millisecond: Long, body: () -> Unit) {
    handler.postDelayed(body, millisecond)
}

//https://weatherapi.market.xiaomi.com/wtr-v3/weather/all?latitude=110&longitude=112&locationKey=weathercn%3A101010100&days=15&appKey=weather20151024&sign=zUFJoAR2ZVrDy1vF3D07&appVersion=87&isGlobal=false&modDevice=&locale=zh_cn