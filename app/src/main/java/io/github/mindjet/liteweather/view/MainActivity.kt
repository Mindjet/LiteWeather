package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.mindjet.liteweather.adapter.CityViewPagerAdapter
import io.github.mindjet.liteweather.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initFab()
    }

    private fun initViewPager() {
        viewPager.adapter = CityViewPagerAdapter(this, supportFragmentManager)
        val white = resources.getColor(android.R.color.white)
        tabLayout.apply {
            setupWithViewPager(viewPager)
            setTabTextColors(white, white)
            setSelectedTabIndicatorColor(white)
        }

    }

    private fun initFab() {

    }
}
