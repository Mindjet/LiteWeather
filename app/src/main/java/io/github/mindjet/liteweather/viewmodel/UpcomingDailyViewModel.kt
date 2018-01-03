package io.github.mindjet.liteweather.viewmodel

import android.view.View
import io.github.mindjet.library.extension.log
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutUpcomingDailyBinding
import io.github.mindjet.liteweather.helper.numToDay
import io.github.mindjet.liteweather.helper.toast
import io.github.mindjet.liteweather.model.DailyForecast
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import java.text.SimpleDateFormat

/**
 * Created by Mindjet on 2017/12/27.
 */
class UpcomingDailyViewModel(private val dailyForecastList: List<DailyForecast>?) : BaseItemViewModel<LayoutUpcomingDailyBinding>() {

    override fun needLayoutId(): Int {
        return R.layout.layout_upcoming_daily
    }

    fun getDay(index: Int): String {
        if (dailyForecastList == null) return "- "
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(dailyForecastList[index].date)
        log(date.day)
        return date.day.numToDay()
    }

    fun handleItemClick(view: View, index: Int) {
        //show popup window
        if (dailyForecastList == null) return
        toast("${view.id}" + index.toString())
    }

}