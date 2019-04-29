package io.github.mindjet.liteweather.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.mindjet.liteweather.util.register
import io.github.mindjet.liteweather.util.unregister

abstract class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregister()
    }

}