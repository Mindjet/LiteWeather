package io.github.mindjet.liteweather.network

import com.google.gson.Gson
import io.github.mindjet.liteweather.model.WeatherAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

object WeatherSrv {

    private const val URL_PREFIX =
        "https://weatherapi.market.xiaomi.com/wtr-v3/weather/all?latitude=0&longitude=0&days=15&appKey=weather20151024&sign=zUFJoAR2ZVrDy1vF3D07&appVersion=87&isGlobal=false&modDevice=&locale=zh_cn"

    private var client: OkHttpClient? = null
        get() {
            if (field == null) {
                field = OkHttpClient()
            }
            return field
        }

    private val gson by lazy { Gson() }

    private val UI: CoroutineContext = Dispatchers.Main

    fun getWeatherAll(cityId: String?, body: (all: WeatherAll) -> Unit) {
        val all = GlobalScope.async {
            val url = "$URL_PREFIX&locationKey=weathercn%3A$cityId"
            val response = client?.newCall(url.toRequest())?.execute()
            val json = response?.body()?.string()
            gson.fromJson<WeatherAll>(json, WeatherAll::class.java)
        }
        runBlocking {
            body.invoke(all.await())
        }
    }


}

fun String.toRequest(): Request {
    return Request.Builder()
        .url(this)
        .build()
}