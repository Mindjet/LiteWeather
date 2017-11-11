package io.github.mindjet.liteweather.helper

import android.content.Intent
import android.widget.TextView
import io.github.mindjet.library.extension.toast
import io.github.mindjet.livemvvm.helper.ActivityStack

/**
 * Created by Mindjet on 2017/10/25.
 */
inline fun <reified T> start() {
    val now = ActivityStack.currentActivity()
    now.startActivity(Intent(now, T::class.java))
}

inline fun <reified T> start(vararg extra: Pair<String, String>) {
    val now = ActivityStack.currentActivity()
    val intent = Intent(now, T::class.java)
    extra.forEach { intent.putExtra(it.first, it.second) }
    now.startActivity(intent)
}

inline fun <reified T> startWithFade() {
    val now = ActivityStack.currentActivity()
    now.startActivity(Intent(now, T::class.java))
    now.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun finishWithFade() {
    val now = ActivityStack.currentActivity()
    now.finish()
    now.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

infix fun TextView.set(string: String) {
    this.text = string
}

fun toast(content: String) {
    val now = ActivityStack.currentActivity()
    now.toast(content)
}

fun Int.dp2px(): Int {
    val now = ActivityStack.currentActivity()
    val density = now.resources.displayMetrics.density
    return (this * density + 0.5f).toInt()
}