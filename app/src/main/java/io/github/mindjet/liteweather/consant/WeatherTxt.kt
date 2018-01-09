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
    const val MID_RAINNY = "中雨"

    fun mapWeatherToColor(string: String): Int {
        return when (string) {
            SUNNY -> R.color.colorSunny
            CLOUDY -> R.color.colorCloudy
            OVERCAST, RAINNY -> R.color.colorOvercast
            FOGGY -> R.color.colorFoggy
            SHOWER -> R.color.colorShower
            SUNNY_INTERVAL -> R.color.colorSunnyInterval
            SLEET -> R.color.colorSleet
            MID_RAINNY -> R.color.colorMidRainny
            else -> R.color.colorDefault
        }
    }

}