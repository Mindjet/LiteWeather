package io.github.mindjet.liteweather.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mindjet on 2017/11/29.
 */
//"brf": "较舒适",
//"txt": "白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。",
//"type": "comf"
data class Lifestyle(
        @SerializedName("brf") val brief: String,
        val txt: String,
        val type: String
)