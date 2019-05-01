package io.github.mindjet.liteweather.util

import android.content.Context
import com.google.gson.reflect.TypeToken
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic
import io.github.mindjet.liteweather.model.City

object CityHelper {

    private const val PINNED_CITIES = "pinned_cities"

    fun getPinnedCities(ctx: Context?): MutableList<City>? {
        return ctx?.getConfigList(PINNED_CITIES, object : TypeToken<MutableList<City>>() {}.type)
    }

    fun addCity(ctx: Context?, city: Basic): Boolean {
        val cities = ctx?.getConfigList<City>(PINNED_CITIES, object : TypeToken<MutableList<City>>() {}.type)
        cities?.forEach {
            if (it.name == city.location) {
                return false
            }
        }
        cities?.add(City(city.location, city.cid))
        ctx?.saveConfigList(PINNED_CITIES, cities)
        return true
    }

}