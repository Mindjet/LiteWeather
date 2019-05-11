package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutVmCityBinding
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.view.CityWeatherVM

class CityPagerAdapter(val context: Context) : PagerAdapter() {

    private var pinnedCities: MutableList<City>? = CityHelper.getPinnedCities(context)

    fun addItem(city: City) {
        pinnedCities?.add(city)
        notifyDataSetChanged()
    }

    fun filterIndexed(condition: (i: Int, city: City) -> Boolean) {
        pinnedCities = pinnedCities?.filterIndexed { index, city ->
            condition.invoke(index, city)
        } as MutableList<City>
        notifyDataSetChanged()
    }

    fun forceUpdate() {
//        pinnedCities?.clear()
//        notifyDataSetChanged()
//        pinnedCities = CityHelper.getPinnedCities(context)
//        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<LayoutVmCityBinding>(
            LayoutInflater.from(context),
            R.layout.layout_vm_city,
            container,
            false
        )
        val view = binding.root
        container.addView(view)

        val vm = CityWeatherVM(pinnedCities?.get(position)?.name)
        binding.vm = vm
        vm.onBind(view, binding)

        binding.executePendingBindings()
        return view
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
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