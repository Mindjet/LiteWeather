package io.github.mindjet.liteweather.viewmodel

import android.databinding.ObservableField
import android.view.View
import io.github.mindjet.library.log
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.ItemHomeBinding
import io.github.mindjet.liteweather.model.SimpleWeather
import io.github.mindjet.liteweather.network.RetrofitInstance
import io.github.mindjet.liteweather.network.WeatherService
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/10/18.
 */
class HomeItemViewModel(val city: String) : BaseItemViewModel<ItemHomeBinding>() {

    val cityName by lazy { ObservableField(city) }

    val temperature by lazy { ObservableField("-") }

    val condition by lazy { ObservableField("-") }

    override fun getLayoutId() = R.layout.item_home

    override fun onAttachedTheFirstTime(binding: ItemHomeBinding) {
        RetrofitInstance.getService(WeatherService::class.java)
                .getSimpleWeather(city)
                .subscribeOn(Schedulers.io())
                .map { it.data?.get(0) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onGetData(it!!) }, { log(it!!) })
    }

    private fun onGetData(data: SimpleWeather) {
        print(data.basic?.city)
        cityName.set(data.basic?.city)
        temperature.set(data.now?.temperature)
        condition.set(data.now?.condition?.text)
    }

    fun onClick(view: View) {

    }

    fun onShare(view: View) {

    }

    fun onMore(view: View) {

    }


}