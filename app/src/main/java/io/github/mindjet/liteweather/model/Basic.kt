package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */

//"cid": "CN101010100",
//"location": "北京",
//"parent_city": "北京",
//"admin_area": "北京",
//"cnty": "中国",
//"lat": "39.90498734",
//"lon": "116.40528870",
//"tz": "0.0"
data class Basic(@SerializedName("cid") val cityId: String,
                 @SerializedName("parent_city") val parentCity: String,
                 @SerializedName("admin_area") val adminArea: String,
                 @SerializedName("cnty") val country: String,
                 val location: String,
                 val lat: String,
                 val lon: String,
                 val tz: String)