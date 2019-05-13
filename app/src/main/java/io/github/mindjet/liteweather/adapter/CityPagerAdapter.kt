package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutVmCityBinding
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.vm.CityWeatherVM

class CityPagerAdapter(private val context: Context) : PagerAdapter() {

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

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val onlyOne = pinnedCities?.size == 0 && position == 0

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            if (onlyOne) R.layout.layout_guide else R.layout.layout_vm_city,
            container,
            false
        )
        val view = binding.root
        container.addView(view)

        //if it' s the only one, which is the guideline page, no need to do viewmodel jobs
        if (onlyOne) {
            return view
        }

        val vm = CityWeatherVM(pinnedCities?.get(position)?.name)

        /*  ViewModel interfaces begin */
        vm.onBind(view, binding)
        vm.initView(view)
        vm.loadData(view)
        /*  ViewModel interfaces end */

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
        return if (pinnedCities?.size == 0 && position == 0) {
            context.resources.getString(R.string.welcome)
        } else {
            pinnedCities?.get(position)?.name
        }
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return if (pinnedCities?.size == 0) 1 else pinnedCities?.size ?: 0
    }

}