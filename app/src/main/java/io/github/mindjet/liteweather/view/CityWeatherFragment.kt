package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.util.conditionColor
import kotlinx.android.synthetic.main.include_basic_info.view.*

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
        HeWeather.getWeatherNow(container?.context, city, object : HeWeather.OnResultWeatherNowBeanListener {
            override fun onSuccess(now: MutableList<Now>?) {
                val data = now?.get(0)!!
                view.apply {
                    clWrapper.setBackgroundResource(data.now.cond_code?.conditionColor()!!)
                    tvTemperature.text = resources.getString(R.string.degree_celsius_unit, data.now.tmp)
                    tvCondition.text = data.now.cond_txt
                    tvFeelingTemperature.text = resources.getString(R.string.feeling_temperature_prefix, data.now.fl)
                    mask1.visibility = View.INVISIBLE
                    mask2.visibility = View.INVISIBLE
                }
            }

            override fun onError(p0: Throwable?) {
                Log.e("zz", p0?.message)
            }
        })
        return view
    }


}