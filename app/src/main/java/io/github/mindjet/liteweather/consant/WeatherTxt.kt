package io.github.mindjet.liteweather.consant

import io.github.mindjet.liteweather.R

/**
 * Created by Mindjet on 2017/10/25.
 */
object WeatherTxt {

    const val SUNNY = "晴"
    const val CLOUDY = "多云"
    const val OVERCAST = "阴"
    const val RAINNY = "雨"
    const val FOGGY = "雾"
    const val SHOWER = "阵雨"
    const val SLEET = "雨夹雪"
    const val SUNNY_INTERVAL = "晴间多云"

    fun getCorrespondingBackground(string: String): Int {
        return when (string) {
            SUNNY -> R.drawable.bg_weather_sunny
            CLOUDY -> R.drawable.bg_weather_cloudy
            OVERCAST -> R.drawable.bg_weather_overcast
            FOGGY -> R.drawable.bg_weather_foggy
            SHOWER -> R.drawable.bg_weather_shower
            else -> R.drawable.bg_weather_default
        }
    }

}