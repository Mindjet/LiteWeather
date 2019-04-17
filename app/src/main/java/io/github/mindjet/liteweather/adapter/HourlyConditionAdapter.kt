package io.github.mindjet.liteweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interfaces.heweather.com.interfacesmodule.bean.weather.hourly.HourlyBase
import io.github.mindjet.liteweather.R

class HourlyConditionAdapter(
    private val data: List<HourlyBase>?,
    private val callback: View.(hourlyCondition: HourlyBase?) -> Unit
) :
    RecyclerView.Adapter<HourlyConditionAdapter.HourlyConditionVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): HourlyConditionVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_condition, parent, false)
        return HourlyConditionVH(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: HourlyConditionVH, position: Int) {
        callback.invoke(viewHolder.itemView, data?.get(position))
    }


    inner class HourlyConditionVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}