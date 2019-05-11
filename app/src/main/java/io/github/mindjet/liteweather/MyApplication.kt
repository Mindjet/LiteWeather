package io.github.mindjet.liteweather

import android.app.Application
import android.content.Context
import interfaces.heweather.com.interfacesmodule.view.HeConfig

class MyApplication : Application() {

    companion object {

        private var _context: Context? = null

        fun getContext(): Context? {
            return _context
        }

        fun setContext(context: Context) {
            _context = context
        }
    }

    override fun onCreate() {
        super.onCreate()
        HeConfig.switchToFreeServerNode()
        HeConfig.init(BuildConfig.ID, BuildConfig.KEY)
        setContext(this)
    }

}