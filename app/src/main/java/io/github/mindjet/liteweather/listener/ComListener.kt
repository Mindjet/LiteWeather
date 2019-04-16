package io.github.mindjet.liteweather.listener

import android.util.Log
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.view.HeWeather

object ComListener {

    const val TAG = "ComListener"

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

    private fun handleError(t: Throwable?) {
        Log.e(TAG, t?.message)
    }

}