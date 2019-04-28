package io.github.mindjet.liteweather.util

import android.content.Context
import io.github.mindjet.liteweather.model.City

class CityHelper {

    companion object {
        private var instance: CityHelper? = null

        @Synchronized
        fun getInstance(): CityHelper? {
            if (instance == null) {
                instance =
                    CityHelper()
            }
            return instance
        }
    }

    fun getPinnedCities(ctx: Context): ArrayList<City> {
        return arrayListOf(City("深圳", "1"))
    }

}