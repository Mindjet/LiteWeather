package io.github.mindjet.liteweather.viewmodel

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutHourlyItemBinding
import io.github.mindjet.liteweather.model.Hourly
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import java.text.SimpleDateFormat

/**
 * Created by Mindjet on 2018/1/9.
 */
class HourlyItemViewModel(private val hourly: Hourly) : BaseItemViewModel<LayoutHourlyItemBinding>() {

    override fun needLayoutId(): Int {
        return R.layout.layout_hourly_item
    }

    fun getTime(): String {
        return extractDayAndTime(hourly.time)
    }

    fun getTemperature(): String {
        return "${hourly.temperature}℃"
    }

    private fun extractDayAndTime(time: String): String {
        val rawTime = time.split(' ')[1]
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(time)
        return "${date.date}日 $rawTime"
    }


}