package io.github.mindjet.liteweather.adapter

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.util.CityHelper
import io.github.mindjet.liteweather.util.PubSub
import io.github.mindjet.liteweather.util.checkFalse
import io.github.mindjet.liteweather.util.checkTrue
import kotlinx.android.synthetic.main.item_city_search.view.*

class CitySearchAdapter(
    private val data: List<Basic>?
) : RecyclerView.Adapter<CitySearchAdapter.CitySearchVH>() {

    override fun onCreateViewHolder(container: ViewGroup, index: Int): CitySearchVH {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_city_search, container, false)
        return CitySearchVH(view)
    }

    override fun getItemCount() = data?.size ?: 0

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(viewHolder: CitySearchVH, index: Int) {
        viewHolder.itemView.apply {
            val city = data?.get(index)
            tvCity.text = "${city?.location}, ${city?.admin_area}"
            flyCityWrapper.setOnClickListener {
                checkFalse(
                    CityHelper.getInstance()?.isCityAlreadyPinned(city)
                ) {
                    PubSub.getInstance().publish("add_city", city)
                }
            }
        }
    }


    inner class CitySearchVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}