package io.github.mindjet.liteweather.util

import android.content.Context

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

    fun getPinnedCities(ctx: Context): ArrayList<String> {
        return arrayListOf("北京市", "深圳市", "佳木斯", "银川市")
    }

}