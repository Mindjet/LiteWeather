package io.github.mindjet.liteweather.util

import io.github.mindjet.liteweather.R

object ConditionHelper {

    fun getConditionColor(conditionCode: String): Int {
        if (conditionCode == "100") return R.color.condition_sunny
        if (conditionCode == "104") return R.color.condition_overcast
        return when (conditionCode[0]) {
            '1' -> R.color.condition_cloudy
            '2' -> R.color.condition_windy
            '3' -> R.color.condition_rainy
            '4' -> R.color.condition_overcast
            '5' -> R.color.condition_muggy
            else -> R.color.condition_overcast
        }
    }

}