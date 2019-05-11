package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper

class CityViewPagerAdapter(context: Context, val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var pinnedCities: MutableList<City>? = CityHelper.getPinnedCities(context)

    fun addItem(city: City) {
        pinnedCities?.add(city)
        notifyDataSetChanged()
    }

    fun filterIndexed(condition: (i: Int, city: City) -> Boolean) {
        pinnedCities = pinnedCities?.filterIndexed(condition) as MutableList<City>
        notifyDataSetChanged()
    }

    override fun getItem(index: Int): Fragment {
        return null!!
    }

    override fun getCount(): Int {
        return pinnedCities?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pinnedCities?.get(position)?.name
    }

}