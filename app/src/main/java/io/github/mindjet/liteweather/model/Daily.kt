package io.github.mindjet.liteweather.model

data class Daily(
    val pubTime: String,
    val sunRiseSet :SunRiseSet,
    val temperature: Temperature
)