package io.github.mindjet.liteweather.network

import io.github.mindjet.library.extension.log
import io.github.mindjet.liteweather.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mindjet on 2017/10/16.
 */

object RetrofitInstance {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor {
                var request = it.request()

                //add api key as query parameter
                val newUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("key", BuildConfig.API_KEY)
                        .build()
                request = request.newBuilder().url(newUrl).build()

                log("---> ${request.method()}: ${request.url()}")
                it.proceed(request)
            }
            .build()

    inline fun <reified T> get(): T {
        return retrofit.create(T::class.java)
    }

}

