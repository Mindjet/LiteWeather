package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.model.Current
import io.github.mindjet.liteweather.model.Index
import io.github.mindjet.liteweather.model.WeatherAll
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

object WeatherSrv {


    private val UI: CoroutineContext = Dispatchers.Main

    fun getWeatherAll(body: (all: WeatherAll) -> Unit) {
        val all = GlobalScope.async {

            val client = OkHttpClient()
            WeatherAll(Current(Index("","")))
        }
        runBlocking {
            body.invoke(all.await())
        }
    }

}