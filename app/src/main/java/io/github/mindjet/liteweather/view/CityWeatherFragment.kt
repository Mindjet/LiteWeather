package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.HourlyConditionAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.conditionColor
import io.github.mindjet.liteweather.util.load
import io.github.mindjet.liteweather.util.runDelay
import io.github.mindjet.liteweather.util.turnTo
import kotlinx.android.synthetic.main.fragment_city.view.*
import kotlinx.android.synthetic.main.include_basic_info.view.*
import kotlinx.android.synthetic.main.include_hourly_condition.view.*
import kotlinx.android.synthetic.main.item_hourly_condition.view.*

class CityWeatherFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var rootView: View? = null
    private var cityName: String? = null

    companion object {
        fun newInstance(city: String, code: String): CityWeatherFragment {
            val bundle = Bundle()
            bundle.putString(Constant.BUNDLE_CITY, city)
            bundle.putString(Constant.BUNDLE_CODE, code)
            val fragment = CityWeatherFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = createFreshView(inflater, container)
        } else {
            if (rootView?.parent != null) {
                val parent = rootView?.parent as ViewGroup
                parent.removeView(rootView)
            }
        }
        return rootView
    }

    private fun createFreshView(inflater: LayoutInflater, container: ViewGroup?): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        cityName = arguments?.getString(Constant.BUNDLE_CITY)
        view.swipeRefreshLayout.setOnRefreshListener(this)
        startLoading(view)
        getCityWeather(view, this::successLoading, this::failLoading)
        return view
    }

    override fun onRefresh() {
        startLoading(rootView)
        runDelay(600) {
            getCityWeather(rootView, this::successLoading, this::failLoading)
        }
    }

    private fun getCityWeather(view: View?, onSuccess: (v: View?) -> Unit, onError: (v: View?) -> Unit) {
        HeWeather.getWeather(
            context, cityName, ComListener.wholeWeather(
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