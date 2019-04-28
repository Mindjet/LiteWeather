package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.view.CityWeatherFragment

class CityViewPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var mPinnedCity: MutableList<City> = CityHelper.getInstance()?.getPinnedCities(context)!!

    fun addItem(city: City) {
        mPinnedCity.add(city)
        notifyDataSetChanged()
    }

    override fun getItem(index: Int): Fragment {
        return CityWeatherFragment.newInstance(mPinnedCity[index].name, mPinnedCity[index].code)
    }

    override fun getCount(): Int {
        return mPinnedCity.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mPinnedCity[position].name
    }

}