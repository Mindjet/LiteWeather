package io.github.mindjet.liteweather.viewmodel

import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.databinding.ItemHomeBinding
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel

/**
 * Created by Mindjet on 2017/10/18.
 */
class HomeItemViewModel : BaseItemViewModel<ItemHomeBinding>() {

    var city: String? = null
    
    var temperature: String? = null

    override fun getLayoutId() = R.layout.item_home

    override fun onAttachedTheFirstTime(binding: ItemHomeBinding) {

    }

}