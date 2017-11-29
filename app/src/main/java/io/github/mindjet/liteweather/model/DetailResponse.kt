package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/11/29.
 */
data class DetailResponse(
        val basic: Basic,
        val update: Update,
        val status: String,
        val now: Now,
        @SerializedName("daily_forecast") val dailyForecast: List<DailyForecast>,
        @SerializedName("hourly") val hourly: List<Hourly>,
        @SerializedName("lifestyle") val lifestyle: List<Lifestyle>
)