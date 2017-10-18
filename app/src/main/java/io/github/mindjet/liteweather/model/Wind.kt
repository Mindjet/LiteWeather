package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
class Wind {

    @SerializedName("deg")
    val degree: String? = null

    @SerializedName("dir")
    val direction: String? = null

    @SerializedName("sc")
    val scale: String? = null

    @SerializedName("spd")
    val speed: String? = null

}