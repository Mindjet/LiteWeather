package io.github.mindjet.liteweather.helper

/**
 * Created by Mindjet on 2018/1/1.
 */

fun Int.numToDay(): String {
    val list = listOf("日", "一", "二", "三", "四", "五", "六")
    return list[this - 1]
}
