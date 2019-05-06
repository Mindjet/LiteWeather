package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.view.View
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CommonAdapter
import io.github.mindjet.liteweather.model.City
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.util.setVerticalLinear
import io.github.mindjet.liteweather.util.showToast
import io.github.mindjet.liteweather.util.turnTo
import kotlinx.android.synthetic.main.activity_city_management.*
import kotlinx.android.synthetic.main.include_recycler_view.*
import kotlinx.android.synthetic.main.item_city_management.view.*

class CityManagementActivity : BaseAppCompatActivity() {

    private lateinit var adapter: CommonAdapter<City>
    private var inEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_management)

        setSupportActionBar(toolbar)

        adapter = CommonAdapter(
            onItemBound = { model, vh, _ ->
                vh.itemView.apply {
                    tvCity.text = model.name
                    tvCitySymbol.text = model.name[0].toString()
                    ivReorder turnTo if (inEditMode) View.VISIBLE else View.GONE
                }
            },
            layoutId = R.layout.item_city_management
        )
        recyclerView.setVerticalLinear(this)
        recyclerView.adapter = adapter

        adapter.addAll(CityHelper.getPinnedCities(this)!!)

        fab.setOnClickListener {
            inEditMode = !inEditMode
            showToast("editmode: $inEditMode")
            adapter.notifyDataSetChanged()
        }
    }

}