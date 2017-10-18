package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
class CommonResponse<out T> {

    @SerializedName("HeWeather5")
    val data: List<T>? = null

}