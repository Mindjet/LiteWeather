package io.github.mindjet.liteweather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_city.view.*

class CityWeatherFragment : Fragment() {

    companion object {
        fun newInstance(city: String): CityWeatherFragment {
            val bundle = Bundle()
            bundle.putString("CITY", city)
            val fragment = CityWeatherFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        val city = arguments?.getString("CITY")
        view.apply {
            tvFragment.text = city
        }
        return view
    }


}