package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/11/1.
 */
//"city": "北京",
//"cnty": "中国",
//"id": "CN101010100",
//"lat": "39.904000",
//"lon": "116.391000",
//"prov": "直辖市"
data class BasicV5(@SerializedName("id") val cityId: String,
                   @SerializedName("cnty") val country: String,
                   @SerializedName("prov") val province: String,
                   val city: String,
                   val lat: String,
                   val lon: String)