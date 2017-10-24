package io.github.mindjet.liteweather.view

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.ActivitySearchBinding
import io.github.mindjet.liteweather.viewmodel.SearchViewModel
import io.github.mindjet.livemvvm.view.BaseActivity
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/23.
 */
class SearchActivity: BaseActivity<ActivitySearchBinding>(){

    override fun needLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun needViewModel(): BaseViewModel<ActivitySearchBinding> {
        return SearchViewModel()
    }

}