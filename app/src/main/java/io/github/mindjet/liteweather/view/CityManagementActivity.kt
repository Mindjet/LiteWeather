package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.util.Log
import android.view.View
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CommonAdapter
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.activity_city_management.*
import kotlinx.android.synthetic.main.include_recycler_view.*
import kotlinx.android.synthetic.main.item_city_management.view.*

class CityManagementActivity : BaseAppCompatActivity() {

    private lateinit var adapter: CommonAdapter<City>
    private var inEditMode = false

    private lateinit var checkStates: MutableList<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_management)
        setSupportActionBar(toolbar)

        //toolbar navigation icon listener
        toolbar.setNavigationOnClickListener { onBackPressed() }

        //floating action button
        fab.setOnClickListener(this::onFabClick)

        adapter = CommonAdapter(
            onItemBound = { model, vh, i ->
                vh.itemView.apply {
                    tvCity.text = model.name
                    tvCitySymbol.text = model.name[0].toString()
                    ivReorder turnTo if (inEditMode) View.VISIBLE else View.GONE
                    checkbox turnTo if (inEditMode) View.VISIBLE else View.GONE
                    checkbox.setOnCheckedChangeListener { _, isChecked -> checkStates[i] = isChecked }
                }
            },
            layoutId = R.layout.item_city_management
        )
        recyclerView.setVerticalLinear(this)
        recyclerView.adapter = adapter

        val cities = CityHelper.getPinnedCities(this)
        adapter.addAll(cities!!)

        //init checkStates, all true as they are chosen at the very first time
        checkStates = cities.map { true } as MutableList<Boolean>
    }

    override fun onBackPressed() {
        if (inEditMode) {
            showToast("please exit edit mode and quit then.")
        } else {
            super.onBackPressed()
        }
    }

    private fun onFabClick(v: View) {
        inEditMode = !inEditMode
        fab.setImageResource(if (inEditMode) R.drawable.ic_done else R.drawable.ic_edit)
        adapter.notifyDataSetChanged()
        if (!inEditMode) {
            checkStates.forEachIndexed { i, value -> Log.e("tag", "$i:$value") }
            val cities = CityHelper.getPinnedCities(this)?.filterIndexed { i, _ -> checkStates[i] } as MutableList<City>
            CityHelper.saveAll(v.context, cities)
            adapter.filterIndexed { i, _ -> checkStates[i] }
            PubSub.getInstance().publish(Constant.SIGNAL_REFRESH_CITY, checkStates)
        }
    }

}