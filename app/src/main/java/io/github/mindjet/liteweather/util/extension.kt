package io.github.mindjet.liteweather.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import io.github.mindjet.liteweather.R

val handler = Handler()
var activities = mutableListOf<AppCompatActivity>()

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

fun AppCompatActivity.register() {
    activities.add(this)
}

fun AppCompatActivity.unregister() {
    activities = activities.filter { it != this } as MutableList<AppCompatActivity>
}

fun Any.killTopActivity() {
    activities[activities.size - 1].finish()
    activities = activities.subList(0, activities.size - 1)
}

fun Any.checkTrue(condition: Boolean?, job: () -> Unit) {
    if (condition != null && condition) {
        job.invoke()
    }
}

fun Any.checkFalse(condition: Boolean?, job: () -> Unit) {
    if (condition != null && !condition) {
        job.invoke()
    }
}

fun Context.showLoadingDialog(): AlertDialog? {
    return AlertDialog.Builder(this)
        .setTitle(R.string.loading)
        .setMessage(R.string.loading)
        .setCancelable(true)
        .show()
}

fun Context.showToast(contentId: Int) {
    val content = this.resources.getString(contentId)
    this.showToast(content)
}

fun Context.showToast(content: Any) {
    Toast.makeText(this, content.toString(), Toast.LENGTH_SHORT).show()
}
//https://weatherapi.market.xiaomi.com/wtr-v3/weather/all?latitude=110&longitude=112&locationKey=weathercn%3A101010100&days=15&appKey=weather20151024&sign=zUFJoAR2ZVrDy1vF3D07&appVersion=87&isGlobal=false&modDevice=&locale=zh_cn