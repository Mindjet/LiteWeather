package io.github.mindjet.liteweather.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.HourlyConditionAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.conditionColor
import io.github.mindjet.liteweather.util.load
import io.github.mindjet.liteweather.util.turnTo
import kotlinx.android.synthetic.main.include_basic_info.view.*
import kotlinx.android.synthetic.main.include_hourly_condition.view.*
import kotlinx.android.synthetic.main.item_hourly_condition.view.*
import kotlinx.android.synthetic.main.layout_vm_city.view.*

class CityWeatherVM(private val cityName: String?) : SwipeRefreshLayout.OnRefreshListener {


    fun onBind(v: View?) {
        Log.e("taggggg", "onBind")
    }

    override fun onRefresh() {
//        startLoading(rootView)
//        runDelay(Constant.LOADING_MIN_SHOWN_TIME) {
//            getCityWeather(rootView, this::successLoading, this::failLoading)
//        }
    }

    private fun getCityWeather(view: View?, onSuccess: (v: View?) -> Unit, onError: (v: View?) -> Unit) {
        HeWeather.getWeather(
            view?.context, cityName, ComListener.wholeWeather(
                {
                    view?.apply {
                        clWrapper.setBackgroundResource(it?.cond_code?.conditionColor()!!)
                        tvTemperature.text = resources.getString(R.string.degree_celsius_unit, it.tmp)
                        tvCondition.text = it.cond_txt
                        tvFeelingTemperature.text = resources.getString(R.string.feeling_temperature_prefix, it.fl)
                        tvPossibilityRainy.text = resources.getString(R.string.possibility_of_rain, it.pcpn)
                        tvHumidity.text = resources.getString(R.string.humidity, it.hum)
                        ivCondition.load("${Constant.CONDITION_ICON_URL_PREFIX}${it.cond_code}.png")
                    }
                },
                {
                    view?.apply {
                        recyclerView.adapter = HourlyConditionAdapter(it) {
                            tvTemperatureItem.text = resources.getString(R.string.degree_celsius_unit, it?.tmp)
                            tvPossibilityRainyItem.text = "${it?.pop}%"
                            ivConditionItem.load("${Constant.CONDITION_ICON_URL_PREFIX}${it?.cond_code}.png")
                            tvTime.text = it?.time!!.split(" ")[1]
                        }
                        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        (recyclerView.adapter as HourlyConditionAdapter).notifyDataSetChanged()
                    }
                }, { onSuccess.invoke(view) }, { onError.invoke(view) }
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

    private fun startLoading(v: View?) {
        v?.apply {
            includeLoadFailedBasicInfo turnTo View.GONE
            includeLoadFailedHourly turnTo View.GONE
            includeLoadingBasicInfo turnTo View.VISIBLE
            includeLoadingHourly turnTo View.VISIBLE
            recyclerView turnTo View.GONE
            clWrapper turnTo View.GONE
        }
    }

}