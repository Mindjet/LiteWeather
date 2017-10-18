package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.BuildConfig
import io.github.mindjet.liteweather.model.CommonResponse
import io.github.mindjet.liteweather.model.SimpleWeather
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Mindjet on 2017/10/18.
 */
interface WeatherService {

    @GET("now?key=" + BuildConfig.API_KEY)
    fun getSimpleWeather(@Query("city") city: String): Observable<CommonResponse<SimpleWeather>>


}