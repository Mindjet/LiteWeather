package io.github.mindjet.liteweather.model

data class DailyBase(
    val date: String,
    val temperature: FromTo,
    val sunRiseSet: FromTo
)