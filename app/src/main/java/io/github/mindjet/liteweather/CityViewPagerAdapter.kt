package io.github.mindjet.liteweather

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CityViewPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var mPinnedCity: List<String> = CityHelper.getInstance()?.getPinnedCities(context)!!

    override fun getItem(index: Int): Fragment {
        return CityWeatherFragment.newInstance(mPinnedCity[index])
    }

    override fun getCount(): Int {
        return mPinnedCity.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mPinnedCity[position]
    }

}