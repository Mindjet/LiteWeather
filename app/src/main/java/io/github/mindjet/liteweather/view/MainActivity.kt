package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CityPagerAdapter
import io.github.mindjet.liteweather.adapter.CityViewPagerAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_hack.*

class MainActivity : BaseAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var subscriptionAdd: PubSub.Subscription
    private lateinit var subscriptionRefresh: PubSub.Subscription
    private lateinit var adapter: CityPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hack)
        initViewPager()
        initDrawerCoordinatorWidgets()

        //response to city add operation
        subscriptionAdd = PubSub.getInstance().subscribe<Basic>(Constant.SIGNAL_ADD_CITY) {
            if (CityHelper.addCity(this, it)) {
                adapter.addItem(City(it.location, it.cid))
                killTopActivity()
                viewPager.currentItem = adapter.count - 1
            } else {
                showToast(R.string.city_exist)
            }
        }

        subscriptionRefresh = PubSub.getInstance()
            .subscribe<MutableList<Boolean>>(Constant.SIGNAL_REFRESH_CITY) {
                adapter.filterIndexed { i, _ -> it[i] }
            }
    }

    private fun initDrawerCoordinatorWidgets() {
        //floating action button
        initFab()
        //toolbar
        setSupportActionBar(toolbar)
        //drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        //navigation view
        navView.setNavigationItemSelectedListener(this)
    }

    private fun initViewPager() {
//        adapter = CityViewPagerAdapter(this, supportFragmentManager)
        adapter = CityPagerAdapter(this)
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
            setOnClickListener { this@MainActivity goto CitySearchActivity::class.java }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptionAdd.unsubscribe()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navCity -> {
                goto(CityManagementActivity::class.java)
            }
            R.id.navConfig -> {
                showToast("Config")
            }
            R.id.navAbout -> {
                showToast("About")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
