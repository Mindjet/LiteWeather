package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper

class CityPagerAdapter(val context: Context) : PagerAdapter() {

    private var pinnedCities: MutableList<City>? = CityHelper.getPinnedCities(context)

    fun addItem(city: City) {
        pinnedCities?.add(city)
        notifyDataSetChanged()
    }

    fun filterIndexed(condition: (i: Int, city: City) -> Boolean) {
        pinnedCities = pinnedCities?.filterIndexed(condition) as MutableList<City>
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.fragment_city, container, false)
        container.addView(view)
        view.apply {

        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pinnedCities?.get(position)?.name
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return pinnedCities?.size ?: 0
    }

}