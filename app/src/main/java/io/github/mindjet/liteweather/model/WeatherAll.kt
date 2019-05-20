package io.github.mindjet.liteweather.model

data class WeatherAll(
    val current: Current,
    val forecastDaily: Daily
)