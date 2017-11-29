package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/11/29.
 */
data class DailyForecast(
        @SerializedName("cond_code_d") val conditionCodeDay: String,
        @SerializedName("cond_code_n") val conditionCodeNight: String,
        @SerializedName("cond_txt_d") val conditionTxtDay: String,
        @SerializedName("cond_txt_n") val conditionTxtNight: String,
        @SerializedName("hum") val humidity: String,
        @SerializedName("pcpn") val precipitation: String,
        @SerializedName("pres") val pressure: String,
        @SerializedName("tmp_max") val temperatureMax: String,
        @SerializedName("tmp_min") val temperatureMin: String,
        @SerializedName("vis") val visibility: String,
        @SerializedName("wind_deg") val windDeg: String,
        @SerializedName("wind_sc") val windScale: String,
        @SerializedName("wind_spd") val windSpeed: String,
        @SerializedName("wind_dir") val windDirection: String,
        val date: String,
        val pop: String,        //降雨概率
        @SerializedName("sr") val sunrise: String,
        @SerializedName("ss") val sunset: String,
        @SerializedName("mr") val moonrise: String,
        @SerializedName("ms") val moonset: String
)