package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interfaces.heweather.com.interfacesmodule.bean.grid.hourly.GridHourly
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.HourlyConditionAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.conditionColor
import io.github.mindjet.liteweather.util.load
import kotlinx.android.synthetic.main.include_basic_info.view.*
import kotlinx.android.synthetic.main.include_hourly_condition.view.*
import kotlinx.android.synthetic.main.item_hourly_condition.view.*

class CityWeatherFragment : Fragment() {

    private var rootView: View? = null

    companion object {
        fun newInstance(city: String): CityWeatherFragment {
            val bundle = Bundle()
            bundle.putString(Constant.BUNDLE_CITY, city)
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
        //TODO extract this annoying coupled logic
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        val city = arguments?.getString(Constant.BUNDLE_CITY)
//        HeWeather.getWeatherNow(container?.context, city, ComListener.nowWeather {
//            view.apply {
//                clWrapper.setBackgroundResource(it?.now?.cond_code?.conditionColor()!!)
//                tvTemperature.text = resources.getString(R.string.degree_celsius_unit, it.now?.tmp)
//                tvCondition.text = it.now?.cond_txt
//                tvFeelingTemperature.text = resources.getString(R.string.feeling_temperature_prefix, it.now?.fl)
//                mask1.visibility = View.INVISIBLE
//                mask2.visibility = View.INVISIBLE
//                ivCondition.load("${Constant.CONDITION_ICON_URL_PREFIX}${it.now?.cond_code}.png")
//            }
//        })
//        HeWeather.getWeatherHourly(context, city, ComListener.hourlyWeather {
//            view.apply {
//                recyclerView.adapter = HourlyConditionAdapter(it) {
//                    tvTemperatureItem.text = resources.getString(R.string.degree_celsius_unit, it?.tmp)
//                    tvPossibilityRainy.text = "${it?.pop}%"
//                    ivConditionItem.load("${Constant.CONDITION_ICON_URL_PREFIX}${it?.cond_code}.png")
//                    tvTime.text = it?.time
//                }
//                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                (recyclerView.adapter as HourlyConditionAdapter).notifyDataSetChanged()
//            }
//        })

        HeWeather.getWeather(context, city, ComListener.wholeWeather(
            {
                view.apply {
                    clWrapper.setBackgroundResource(it?.cond_code?.conditionColor()!!)
                    tvTemperature.text = resources.getString(R.string.degree_celsius_unit, it.tmp)
                    tvCondition.text = it.cond_txt
                    tvFeelingTemperature.text = resources.getString(R.string.feeling_temperature_prefix, it.fl)
                    mask1.visibility = View.INVISIBLE
                    mask2.visibility = View.INVISIBLE
                    ivCondition.load("${Constant.CONDITION_ICON_URL_PREFIX}${it.cond_code}.png")
                }
            },
            {
                view.apply {
                    recyclerView.adapter = HourlyConditionAdapter(it) {
                        tvTemperatureItem.text = resources.getString(R.string.degree_celsius_unit, it?.tmp)
                        tvPossibilityRainy.text = "${it?.pop}%"
                        ivConditionItem.load("${Constant.CONDITION_ICON_URL_PREFIX}${it?.cond_code}.png")
                        tvTime.text = it?.time
                    }
                    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    (recyclerView.adapter as HourlyConditionAdapter).notifyDataSetChanged()
                }
            }
        ))
        return view
    }


}