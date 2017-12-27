package io.github.mindjet.liteweather.viewmodel

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.LayoutUpcomingDailyBinding
import io.github.mindjet.liteweather.helper.toast
import io.github.mindjet.liteweather.model.DailyForecast
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import java.text.SimpleDateFormat

/**
 * Created by Mindjet on 2017/12/27.
 */
class UpcomingDailyViewModel(val dailyForecastList: List<DailyForecast>?) : BaseItemViewModel<LayoutUpcomingDailyBinding>() {

    override fun needLayoutId(): Int {
        return R.layout.layout_upcoming_daily
    }

//    fun onClick(view: View) {
//        when (view.id) {
//            R.id.daily_item_1 -> handleItemClick(0)
//            R.id.daily_item_2 -> handleItemClick(1)
//            R.id.daily_item_3 -> handleItemClick(2)
//        }
//    }

    fun getDay(index: Int): String {
        if (dailyForecastList == null) return "--"
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(dailyForecastList[index].date)
        return date.day.toString()
    }

    fun handleItemClick(index: Int) {
        //show popup window
        if (dailyForecastList == null) return
        toast(index.toString())
    }

}