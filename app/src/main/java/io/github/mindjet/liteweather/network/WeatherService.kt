package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.model.CommonResponse
import io.github.mindjet.liteweather.model.NowResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Mindjet on 2017/10/18.
 */
interface WeatherService {

    @GET("now")
    fun getSimpleWeather(@Query("location") city: String): Observable<CommonResponse<NowResponse>>


}