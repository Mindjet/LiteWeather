package io.github.mindjet.liteweather.view

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.ActivityDetailBinding
import io.github.mindjet.liteweather.viewmodel.DetailViewModel
import io.github.mindjet.livemvvm.view.BaseActivity
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/11/11.
 */
class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun needLayoutId(): Int {
        return R.layout.activity_detail
    }

    override fun needViewModel(): BaseViewModel<ActivityDetailBinding> {
        return DetailViewModel()
    }

}