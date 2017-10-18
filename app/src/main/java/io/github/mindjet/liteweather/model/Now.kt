package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
class Now {

    @SerializedName("cond")
    val condition: Condition? = null

    @SerializedName("fl")
    val feelTemp: String? = null

    @SerializedName("hum")
    val humidity: String? = null

    @SerializedName("pcpn")
    val precipitation: String? = null

    @SerializedName("pres")
    val pressure: String? = null

    @SerializedName("tmp")
    val temperature: String? = null

    @SerializedName("vis")
    val visibility: String? = null

    @SerializedName("wind")
    val wind: Wind? = null

}