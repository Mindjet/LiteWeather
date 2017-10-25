package io.github.mindjet.liteweather.viewmodel

import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.view.View
import io.github.mindjet.library.log
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.consant.WeatherTxt
import io.github.mindjet.liteweather.databinding.ItemWeatherBinding
import io.github.mindjet.liteweather.model.SimpleWeather
import io.github.mindjet.liteweather.network.RetrofitInstance
import io.github.mindjet.liteweather.network.WeatherService
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/10/18.
 */
class WeatherItemViewModel(val city: String) : BaseItemViewModel<ItemWeatherBinding>() {

    val cityName by lazy { ObservableField(city) }
    val temperature by lazy { ObservableField("-") }
    val condition by lazy { ObservableField("-") }
    val background by lazy { ObservableField<Drawable>() }

    override fun needLayoutId() = R.layout.item_weather

    override fun onAttachedTheFirstTime(binding: ItemWeatherBinding) {
        background.set(context?.resources?.getDrawable(R.drawable.bg_weather_default))
        updateData()
    }

    private fun onReceiveData(data: SimpleWeather) {
        print(data.basic?.city)
        cityName.set(data.basic?.city)
        temperature.set(data.now?.temperature)
        condition.set(data.now?.condition?.text)
        background.set(context?.resources?.getDrawable(WeatherTxt.getCorrespondingBackground(data.now?.condition?.text!!)))
    }

    private fun updateData() {
        RetrofitInstance.getService(WeatherService::class.java)
                .getSimpleWeather(city)
                .subscribeOn(Schedulers.io())
                .map { it.data?.get(0) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onReceiveData(it!!) }, { log(it!!) })
    }

    fun onClick(view: View) {

    }

    fun onShare(view: View) {

    }

    fun onMore(view: View) {

    }


}