package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/10/18.
 */
class NowResponse {

    @SerializedName("basic")
    val basic: Basic? = null

    @SerializedName("now")
    val now: Now? = null

    val status: String? = null

    val update: Update? = null

}