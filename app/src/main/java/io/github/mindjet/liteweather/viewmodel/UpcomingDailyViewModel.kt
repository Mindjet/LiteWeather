package io.github.mindjet.liteweather.viewmodel

import android.graphics.drawable.ColorDrawable
import android.view.View
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.consant.WeatherTxt
import io.github.mindjet.liteweather.databinding.LayoutUpcomingDailyBinding
import io.github.mindjet.liteweather.helper.PopupWindowGen
import io.github.mindjet.liteweather.helper.numToDay
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
        if (dailyForecastList == null) return "-"
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(dailyForecastList[index].date)
        return date.day.numToDay()
    }

    fun handleItemClick(view: View, index: Int) {
        //show popup window
        if (dailyForecastList == null) return
        val item = dailyForecastList[index]
        val popWindow = PopupWindowGen.with(context) {
            width = 200
            layoutId = R.layout.pop_up_daily_forecast
            addContent(R.id.tv_condition, item.conditionTxtDay)
            addContent(R.id.tv_date, item.date)
            addContent(R.id.tv_temperature, "${item.temperatureMin}℃~${item.temperatureMax}℃")
            addContent(R.id.tv_humidity, "${item.humidity}%")
            addContent(R.id.tv_visibility, "${item.visibility}km")
            addContent(R.id.tv_percentage_of_rain, "${item.pop}%")
            addContent(R.id.tv_sunrise_sunset, "${item.sunrise}/${item.sunset}")
            addContent(R.id.tv_moonrise_moonset, "${item.moonrise}/${item.moonset}")
            build()
        }
        val header = popWindow.contentView.findViewById<View>(R.id.lly_header)
        header.background = ColorDrawable(context.resources.getColor(WeatherTxt.mapWeatherToColor(item.conditionTxtDay)))
        popWindow.showAsDropDown(view)
    }

}