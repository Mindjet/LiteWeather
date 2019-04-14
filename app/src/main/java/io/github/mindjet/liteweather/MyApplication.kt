package io.github.mindjet.liteweather

import android.app.Application
import interfaces.heweather.com.interfacesmodule.view.HeConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        HeConfig.switchToFreeServerNode()
        HeConfig.init(BuildConfig.ID, BuildConfig.KEY)
    }

}