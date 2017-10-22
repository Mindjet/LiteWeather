package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
data class Condition(val code: String, @SerializedName("txt") val text: String)