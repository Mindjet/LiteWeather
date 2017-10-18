package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mindjet on 2017/10/16.
 */

object RetrofitInstance {

    private val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> getService(clazz: Class<T>): T {
        return mRetrofit.create(clazz)
    }

}

