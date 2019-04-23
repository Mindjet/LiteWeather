package io.github.mindjet.liteweather.listener

import android.util.Log
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import interfaces.heweather.com.interfacesmodule.bean.search.Search
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.Hourly
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.HourlyBase
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase
import interfaces.heweather.com.interfacesmodule.view.HeWeather

object ComListener {

    private const val TAG = "ComListener"

    fun wholeWeather(
        nowBody: (now: NowBase?) -> Unit,
        hourlyBody: (hourly: List<HourlyBase>) -> Unit,
        onSuccess: () -> Unit = {},
        onError: (t: Throwable?) -> Unit = {}
    ): HeWeather.OnResultWeatherDataListBeansListener {
        return object : HeWeather.OnResultWeatherDataListBeansListener {
            override fun onSuccess(data: MutableList<Weather>?) {
                onSuccess.invoke()
                if (data == null) {
                    onError(Throwable("response is null"))
                    return
                }
                nowBody.invoke(data[0].now)
                if (checkIfNull(data[0].hourly)) {
                    hourlyBody.invoke(data[0].hourly)
                }
            }

            override fun onError(t: Throwable?) {
                onError.invoke(t)
                handleError(t)
            }

        }
    }

    fun citySearch(body: (cities: List<Basic>?) -> Unit): HeWeather.OnResultSearchBeansListener {
        return object : HeWeather.OnResultSearchBeansListener {
            override fun onSuccess(city: Search?) {
                body.invoke(city?.basic)
            }

            override fun onError(p0: Throwable?) {
                handleError(p0)
            }

        }
    }

    fun checkIfNull(o: Any?): Boolean {
        if (o == null) {
            handleError(Throwable("data is null"))
            return false
        }
        return true
    }

    fun nowWeather(body: (now: Now?) -> Unit): HeWeather.OnResultWeatherNowBeanListener {
        return object : HeWeather.OnResultWeatherNowBeanListener {
            override fun onSuccess(data: MutableList<Now>?) {
                if (data == null) onError(Throwable("response is null"))
                body.invoke(data?.get(0))
            }

            override fun onError(p0: Throwable?) {
                handleError(p0)
            }

        }
    }

    fun hourlyWeather(body: (hourlyConditionList: MutableList<HourlyBase>?) -> Unit): HeWeather.OnResultWeatherHourlyBeanListener {
        return object : HeWeather.OnResultWeatherHourlyBeanListener {
            override fun onSuccess(data: MutableList<Hourly>?) {
                if (data == null) onError(Throwable("response is null"))
                body.invoke(data?.get(0)?.hourly)
            }

            override fun onError(p0: Throwable?) {
                handleError(p0)
            }

        }
    }

    private fun handleError(t: Throwable?) {
        Log.e(TAG, t?.message)
    }

}