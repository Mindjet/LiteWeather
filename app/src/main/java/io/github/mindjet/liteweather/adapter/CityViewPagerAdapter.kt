package io.github.mindjet.liteweather.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.view.CityWeatherFragment

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

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //拿到缓存的fragment，如果没有缓存的，就新建一个，新建发生在fragment的第一次初始化时
        var f = super.instantiateItem(container, position) as CityWeatherFragment
        val fragmentTag = f.tag
        if (f !== getItem(position)) {
            //如果是新建的fragment，f 就和getItem(position)是同一个fragment，否则进入下面
            val ft = fm.beginTransaction()
            //移除旧的fragment
            ft.remove(f)
            //换成新的fragment
            f = getItem(position) as CityWeatherFragment
            //添加新fragment时必须用前面获得的tag
            ft.add(container.id, f, fragmentTag)
            ft.attach(f)
            ft.commitAllowingStateLoss()
        }
        return f
    }

    override fun getItem(index: Int): Fragment {
        return CityWeatherFragment.newInstance(pinnedCities?.get(index)?.name, pinnedCities?.get(index)?.code)
    }

    override fun getCount(): Int {
        return pinnedCities?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pinnedCities?.get(position)?.name
    }

}