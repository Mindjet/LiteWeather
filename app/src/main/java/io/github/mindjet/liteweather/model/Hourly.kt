package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/11/29.
 */
//"cloud": "46",
//"cond_code": "103",
//"cond_txt": "晴间多云",
//"dew": "20.7",
//"hum": "78",
//"pop": "6",
//"pres": "1017",
//"time": "2017-11-29 22:00",
//"tmp": "24",
//"wind_deg": "97",
//"wind_dir": "东风",
//"wind_sc": "微风",
//"wind_spd": "9"
data class Hourly(@SerializedName("cond_code") val conditionCode: String,
                  @SerializedName("cond_txt") val conditionTxt: String,
                  @SerializedName("hum") val humidity: String,
                  @SerializedName("pres") val pressure: String,
                  @SerializedName("tmp") val temperature: String,
                  @SerializedName("wind_deg") val windDeg: String,
                  @SerializedName("wind_sc") val windScale: String,
                  @SerializedName("wind_spd") val windSpeed: String,
                  @SerializedName("wind_dir") val windDirection: String,
                  val cloud: String, //云量
                  val dew: String, //露点温度
                  val pop: String, //降雨概率
                  val time: String
)