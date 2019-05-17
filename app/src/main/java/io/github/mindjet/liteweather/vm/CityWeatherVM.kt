package io.github.mindjet.liteweather.vm

import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.View
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.HourlyBase
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.MyApplication
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CommonAdapter
import io.github.mindjet.liteweather.adapter.CommonVH
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.databinding.LayoutVmCityBinding
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.network.WeatherSrv
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.item_hourly_condition.view.*

/**
 * ViewModel for layout_vm_city
 */
class CityWeatherVM(private val city: City?) : BaseObservable(), IViewModel {

    private lateinit var _binding: LayoutVmCityBinding

    private var loadedOnce = false

    var loading = HaloOb.Bool(true)
    var loadFailed = HaloOb.Bool(false)

    var background = HaloOb.Int(R.color.condition_overcast)

    var temperature = HaloOb.String()
    var condition = HaloOb.String()
    var feelingTemperature = HaloOb.String()
    var possibilityOfRain = HaloOb.String()
    var humidity = HaloOb.String()

    var conditionIcon = HaloOb.String()

    var adapter = CommonAdapter(onItemBound = this::onItemBound, layoutId = R.layout.item_hourly_condition)

    override fun onBind(v: View?, binding: ViewDataBinding) {
        _binding = binding as LayoutVmCityBinding
        _binding.vm = this
    }

    override fun initView(v: View?) {

    }

    override fun loadData(v: View?) {
        WeatherSrv.getWeatherAll(city?.code) {
            Log.e("sss", "${it.current.feelsLike.value}${it.current.feelsLike.unit}")
        }
        onRefresh()
    }

    private fun onItemBound(hourly: HourlyBase, vh: CommonVH, i: Int) {
        vh.itemView.apply {
            tvTemperatureItem.text = resources.getString(R.string.degree_celsius_unit, hourly.tmp)
            tvPossibilityRainyItem.text = "${hourly.pop}%"
            ivConditionItem.load("${Constant.CONDITION_ICON_URL_PREFIX}${hourly.cond_code}.png")
            tvTime.text = hourly.time.split(" ")[1]
        }
    }

    fun onRefresh() {
        startLoading()
        runDelay(Constant.LOADING_MIN_SHOWN_TIME) {
            getCityWeather()
        }
    }

    private fun getCityWeather() {
        HeWeather.getWeather(
            MyApplication.getContext(), city?.name, ComListener.wholeWeather(
                {
                    loadedOnce = true
                    background.value = it?.cond_code?.conditionColor()!!
                    temperature.value = getStringEx(R.string.degree_celsius_unit, it.tmp)
                    condition.value = it.cond_txt
                    feelingTemperature.value = getStringEx(R.string.feeling_temperature_prefix, it.fl)
                    possibilityOfRain.value = getStringEx(R.string.possibility_of_rain, it.pcpn)
                    humidity.value = getStringEx(R.string.humidity, it.hum)
                    conditionIcon.value = "${Constant.CONDITION_ICON_URL_PREFIX}${it.cond_code}.png"
                },
                {
                    adapter.addAll(it)
                },
                {
                    loading.value = false
                    loadFailed.value = false
                    _binding.swipeRefreshLayout.isRefreshing = false
                },
                {
                    loadFailed.value = true
                    loading.value = false
                    _binding.swipeRefreshLayout.isRefreshing = false
                }
            )
        )
    }

    private fun startLoading() {
        loading.value = !loadedOnce && true
        loadFailed.value = false
    }

}