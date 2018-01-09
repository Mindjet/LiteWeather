package io.github.mindjet.liteweather.viewmodel

import android.support.v7.widget.LinearLayoutManager
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutHourlyBinding
import io.github.mindjet.liteweather.model.Hourly
import io.github.mindjet.liteweather.recycler_view.ListAdapter
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel

/**
 * Created by Mindjet on 2018/1/9.
 */
class HourlyViewModel(private val hourlyList: List<Hourly>) : BaseItemViewModel<LayoutHourlyBinding>() {

    private val recyclerView by lazy { binding.recyclerView }
    private val adapter by lazy { ListAdapter<HourlyItemViewModel>() }

    override fun needLayoutId(): Int {
        return R.layout.layout_hourly
    }

    override fun onAttachedTheFirstTime(binding: LayoutHourlyBinding) {
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        hourlyList.forEach({ adapter.add(HourlyItemViewModel(it)) })
        adapter.notifyItemRangeInserted(0, hourlyList.size)
    }

}