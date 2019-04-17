package io.github.mindjet.liteweather.listener

import android.util.Log
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.Hourly
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.HourlyBase
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase
import interfaces.heweather.com.interfacesmodule.view.HeWeather

object ComListener {

    const val TAG = "ComListener"

    fun wholeWeather(
        nowBody: (now: NowBase?) -> Unit,
        hourlyBody: (hourly: List<HourlyBase>) -> Unit
    ): HeWeather.OnResultWeatherDataListBeansListener {
        return object : HeWeather.OnResultWeatherDataListBeansListener {
            override fun onSuccess(data: MutableList<Weather>?) {
                if (data == null) {
                    onError(Throwable("response is null"))
                    return
                }
                nowBody.invoke(data[0].now)
                if (data[0].hourly == null) {
                    onError(Throwable("hourly data is null"))
                    return
                }
                hourlyBody.invoke(data[0].hourly)
            }

            override fun onError(t: Throwable?) {
                handleError(t)
            }

        }
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