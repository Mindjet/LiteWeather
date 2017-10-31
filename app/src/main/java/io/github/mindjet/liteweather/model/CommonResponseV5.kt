package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/31.
 */
data class CommonResponseV5<out T>(@SerializedName("HeWeather5") val data: List<T>)