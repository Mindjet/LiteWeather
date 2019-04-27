package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import interfaces.heweather.com.interfacesmodule.bean.Lang
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CitySearchAdapter
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.turnTo
import kotlinx.android.synthetic.main.activity_city_search.*

class CitySearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)

        etInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                HeWeather.getSearch(
                    this@CitySearchActivity,
                    v.text.toString(),
                    "CN",
                    10,
                    Lang.CHINESE_SIMPLIFIED,
                    ComListener.citySearch(
                        {
                            recyclerView.adapter = CitySearchAdapter(it)
                            recyclerView.layoutManager =
                                LinearLayoutManager(this@CitySearchActivity, RecyclerView.VERTICAL, false)
                            (recyclerView.adapter as CitySearchAdapter).notifyDataSetChanged()
                            cvNoCity turnTo View.GONE
                            recyclerView turnTo View.VISIBLE
                        },
                        {
                            cvNoCity turnTo View.VISIBLE
                            recyclerView turnTo View.GONE
                        })
                )
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}