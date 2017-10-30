package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
//"cond_code": "101",
//"cond_txt": "多云",
//"fl": "16",
//"hum": "73",
//"pcpn": "0",
//"pres": "1017",
//"tmp": "14",
//"vis": "1",
//"wind_deg": "11",
//"wind_dir": "北风",
//"wind_sc": "微风",
//"wind_spd": "6"
data class Now(
        @SerializedName("cond_code") val conditionCode: String,
        @SerializedName("cond_txt") val conditionTxt: String,
        @SerializedName("fl") val feelTemp: String,
        @SerializedName("hum") val humidity: String,
        @SerializedName("pcpn") val precipitation: String,
        @SerializedName("pres") val pressure: String,
        @SerializedName("tmp") val temperature: String,
        @SerializedName("vis") val visibility: String,
        @SerializedName("wind_deg") val windDeg: String,
        @SerializedName("wind_sc") val windScale: String,
        @SerializedName("wind_spd") val windSpeed: String,
        @SerializedName("wind_dir") val windDirection: String
)