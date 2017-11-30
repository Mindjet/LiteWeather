package io.github.mindjet.liteweather.viewmodel

import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.view.View
import io.github.mindjet.library.extension.log
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.consant.Constant
import io.github.mindjet.liteweather.consant.WeatherTxt
import io.github.mindjet.liteweather.databinding.ItemWeatherBinding
import io.github.mindjet.liteweather.helper.EasyBus
import io.github.mindjet.liteweather.helper.PopupWindowGen
import io.github.mindjet.liteweather.helper.start
import io.github.mindjet.liteweather.model.NowResponse
import io.github.mindjet.liteweather.network.RetrofitInstance
import io.github.mindjet.liteweather.network.WeatherService
import io.github.mindjet.liteweather.view.DetailActivity
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/10/18.
 */
class WeatherItemViewModel(val city: String) : BaseItemViewModel<ItemWeatherBinding>() {

    val cityName by lazy { ObservableField(city) }
    val temperature by lazy { ObservableField("--") }
    val condition by lazy { ObservableField("--") }
    val background by lazy { ObservableField<Drawable>() }

    private val popupWindow by lazy {
        PopupWindowGen.with(context) {
            layoutId = R.layout.popup_window
            addListener(R.id.popup_refresh, { onRefresh(it) })
            addListener(R.id.popup_delete, { onDelete(it) })
            build()
        }
    }

    override fun needLayoutId() = R.layout.item_weather

    override fun onAttachedTheFirstTime(binding: ItemWeatherBinding) {
        background.set(context?.resources?.getDrawable(R.drawable.bg_weather_default))
        updateData()
    }

    private fun onReceiveData(data: NowResponse) {
        cityName.set(data.basic?.location)
        temperature.set(data.now?.temperature)
        condition.set(data.now?.conditionTxt)
        background.set(context?.resources?.getDrawable(WeatherTxt.getCorrespondingBackground(data.now?.conditionTxt!!)))
    }

    private fun updateData() {
        RetrofitInstance.get<WeatherService>()
                .getSimpleWeather(city)
                .subscribeOn(Schedulers.io())
                .map { it.data?.get(0) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onReceiveData(it!!) }, { log(it!!) })
    }

    fun onClick(view: View) {
        start<DetailActivity>(
                Pair(Constant.INTENT_CITYNAME, cityName.get()),
                Pair(Constant.INTENT_CONDITION, condition.get()),
                Pair(Constant.INTENT_TEMPERATURE, temperature.get()))
    }

    fun onShare(view: View) {

    }

    fun onMore(view: View) =
            popupWindow.showAsDropDown(view)

    private fun onRefresh(view: View) {
        temperature.set("--")
        condition.set("--")
        updateData()
        popupWindow.dismiss()
    }

    private fun onDelete(view: View) {
        EasyBus.send(this, Constant.SIGNAL_DELETE_ITEM)
        popupWindow.dismiss()
    }

}