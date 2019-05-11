package io.github.mindjet.liteweather.view

import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.MyApplication
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.include_basic_info.view.*
import kotlinx.android.synthetic.main.include_hourly_condition.view.*
import kotlinx.android.synthetic.main.layout_vm_city.view.*

/**
 * ViewModel for layout_vm_city
 */
class CityWeatherVM(private val cityName: String?) : BaseObservable(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: ViewDataBinding? = null

    var loading: Boolean = true
    var loadFailed: Boolean = false

    var background = HaloOb.Int(R.color.condition_overcast)

    var temperature = HaloOb.String()
    var condition = HaloOb.String()
    var feelingTemperature = HaloOb.String()
    var possibilityOfRain = HaloOb.String()
    var humidity = HaloOb.String()

    var conditionIcon = HaloOb.String()


    fun onBind(v: View?, binding: ViewDataBinding) {
        _binding = binding
        Log.e("taggggg", "onBind")
        onRefresh()
    }

    override fun onRefresh() {
        startLoading()
        runDelay(Constant.LOADING_MIN_SHOWN_TIME) {
            getCityWeather()
        }
    }

    private fun getCityWeather() {
        HeWeather.getWeather(
            MyApplication.getContext(), cityName, ComListener.wholeWeather(
                {
                    background.value = it?.cond_code?.conditionColor()!!
                    temperature.value = getStringEx(R.string.degree_celsius_unit, it.tmp)
                    condition.value = it.cond_txt
                    feelingTemperature.value = getStringEx(R.string.feeling_temperature_prefix, it.fl)
                    possibilityOfRain.value = getStringEx(R.string.possibility_of_rain, it.pcpn)
                    humidity.value = getStringEx(R.string.humidity, it.hum)
//                    conditionIcon.value = "${Constant.CONDITION_ICON_URL_PREFIX}${it.cond_code}.png"
                },
                {
                    //        view?.apply {
//            recyclerView.adapter = HourlyConditionAdapter(it) {
//                tvTemperatureItem.text = resources.getString(R.string.degree_celsius_unit, it?.tmp)
//                tvPossibilityRainyItem.text = "${it?.pop}%"
//                ivConditionItem.load("${Constant.CONDITION_ICON_URL_PREFIX}${it?.cond_code}.png")
//                tvTime.text = it?.time!!.split(" ")[1]
//            }
//            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            (recyclerView.adapter as HourlyConditionAdapter).notifyDataSetChanged()
//        }
                },
                {
                    loading = false
                    loadFailed = false
                },
                {

                }
            )
        )
    }

    private fun failLoading(v: View?) {
        v?.apply {
            includeLoadFailedBasicInfo turnTo View.GONE
            includeLoadFailedHourly turnTo View.GONE
            includeLoadingBasicInfo turnTo View.VISIBLE
            includeLoadingHourly turnTo View.VISIBLE
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun successLoading(v: View?) {
        v?.apply {
            includeLoadFailedBasicInfo turnTo View.GONE
            includeLoadFailedHourly turnTo View.GONE
            includeLoadingBasicInfo turnTo View.GONE
            includeLoadingHourly turnTo View.GONE
            clWrapper turnTo View.VISIBLE
            recyclerView turnTo View.VISIBLE
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun startLoading() {
//        v?.apply {
//            includeLoadFailedBasicInfo turnTo View.GONE
//            includeLoadFailedHourly turnTo View.GONE
//            includeLoadingBasicInfo turnTo View.VISIBLE
//            includeLoadingHourly turnTo View.VISIBLE
//            recyclerView turnTo View.GONE
//            clWrapper turnTo View.GONE
//        }
        loading = true
        loadFailed = false

    }

}