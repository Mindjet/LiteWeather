package io.github.mindjet.liteweather.view

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import interfaces.heweather.com.interfacesmodule.bean.Lang
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CitySearchAdapter
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.giveupFocus
import io.github.mindjet.liteweather.util.showLoadingDialog
import io.github.mindjet.liteweather.util.showToast
import io.github.mindjet.liteweather.util.turnTo
import kotlinx.android.synthetic.main.activity_city_search.*

class CitySearchActivity : BaseAppCompatActivity() {

    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        etInput.requestFocus()
        etInput.findFocus()

        etInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loadingDialog = showLoadingDialog()
                giveupFocus()
                recyclerView turnTo View.INVISIBLE
                HeWeather.getSearch(
                    this@CitySearchActivity,
                    v.text.toString(),
                    "CN",
                    20,
                    Lang.CHINESE_SIMPLIFIED,
                    ComListener.citySearch(
                        {
                            recyclerView.adapter = CitySearchAdapter(it)
                            (recyclerView.adapter as CitySearchAdapter).notifyDataSetChanged()
                            recyclerView turnTo View.VISIBLE
                            loadingDialog?.hide()
                        },
                        {
                            showToast(R.string.no_city_found)
                            loadingDialog?.dismiss()
                        })
                )
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}