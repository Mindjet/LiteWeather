package io.github.mindjet.liteweather.util

fun String.conditionColor(): Int {
    return ConditionHelper.getConditionColor(this)
}