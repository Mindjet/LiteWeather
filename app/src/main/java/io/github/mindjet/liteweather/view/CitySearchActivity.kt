package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import interfaces.heweather.com.interfacesmodule.bean.Lang
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import interfaces.heweather.com.interfacesmodule.view.HeWeather
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.adapter.CommonAdapter
import io.github.mindjet.liteweather.adapter.CommonVH
import io.github.mindjet.liteweather.constant.Constant
import io.github.mindjet.liteweather.listener.ComListener
import io.github.mindjet.liteweather.util.*
import kotlinx.android.synthetic.main.activity_city_search.*
import kotlinx.android.synthetic.main.item_city_search.view.*

class CitySearchActivity : BaseAppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null
    private val adapter = CommonAdapter(onItemBound = this::onItemBound, layoutId = R.layout.item_city_search)


    private fun onItemBound(city: Basic, vh: CommonVH, i: Int) {
        vh.itemView.apply {
            tvCity.text = "${city.location}, ${city.admin_area}"
            flyCityWrapper.setOnClickListener {
                PubSub.getInstance().publish(Constant.SIGNAL_ADD_CITY, city)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)

        recyclerView.setVerticalLinear(this)
        recyclerView.adapter = adapter
        etInput.requestFocus()
        etInput.findFocus()

        etInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                loadingDialog = showLoadingDialog()
                giveupFocus()
                recyclerView turnTo View.INVISIBLE
                runDelay(Constant.LOADING_MIN_SHOWN_TIME) {
                    HeWeather.getSearch(
                        this@CitySearchActivity,
                        v.text.toString(),
                        "CN",
                        20,
                        Lang.CHINESE_SIMPLIFIED,
                        ComListener.citySearch(
                            {
                                loadingDialog?.dismiss()
                                adapter.clear()
                                adapter.addAll(it!!)
                                recyclerView turnTo View.VISIBLE
                            },
                            {
                                showToast(R.string.no_city_found)
                                loadingDialog?.dismiss()
                            })
                    )
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}