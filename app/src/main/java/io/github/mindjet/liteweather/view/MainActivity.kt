package io.github.mindjet.liteweather.view

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.ActivityMainBinding
import io.github.mindjet.liteweather.viewmodel.MainViewModel
import io.github.mindjet.livemvvm.view.BaseActivity
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/17.
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun needLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun needViewModel(): BaseViewModel<ActivityMainBinding> {
        return MainViewModel()
    }

}