package io.github.mindjet.liteweather.view

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CityViewPagerAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompatActivity() {

    private lateinit var subscription: PubSub.Subscription
    private lateinit var adapter: CityViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initFab()

        //response to city add operation
        subscription = PubSub.getInstance().subscribe<Basic>(Constant.SIGNAL_ADD_CITY) {
            if (CityHelper.addCity(this, it)) {
                adapter.addItem(City(it.location, it.cid))
                killTopActivity()
                viewPager.currentItem = adapter.count - 1
            } else {
                showToast(R.string.city_exist)
            }
        }
    }

    private fun initViewPager() {
        adapter = CityViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = adapter
        val white = resources.getColor(android.R.color.white)
        tabLayout.apply {
            setupWithViewPager(viewPager)
            setTabTextColors(white, white)
            setSelectedTabIndicatorColor(white)
        }
    }

    private fun initFab() {
        fab.apply {
            setImageResource(android.R.drawable.ic_input_add)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.white))
            }
            setOnClickListener { this@MainActivity goto CitySearchActivity::class.java }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.unsubscribe()
    }

}
