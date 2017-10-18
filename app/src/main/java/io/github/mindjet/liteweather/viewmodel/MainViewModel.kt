package io.github.mindjet.liteweather.viewmodel

import io.github.mindjet.library.log
import io.github.mindjet.liteweather.databinding.ActivityMainBinding
import io.github.mindjet.liteweather.network.RetrofitInstance
import io.github.mindjet.liteweather.network.WeatherService
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/10/17.
 */
class MainViewModel : BaseViewModel<ActivityMainBinding>() {


    override fun onAttached(binding: ActivityMainBinding) {
        RetrofitInstance.getService(WeatherService::class.java)
                .getSimpleWeather("shenzhen")
                .subscribeOn(Schedulers.io())
                .map { it.data?.get(0) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ log(it?.basic?.city!!) }, { log(it) })
    }

}