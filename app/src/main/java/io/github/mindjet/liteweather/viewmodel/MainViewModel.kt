package io.github.mindjet.liteweather.viewmodel

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import io.github.mindjet.liteweather.databinding.ActivityMainBinding
import io.github.mindjet.liteweather.recycler_view.ListAdapter
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/17.
 */
class MainViewModel : BaseViewModel<ActivityMainBinding>() {

    private val adapter by lazy { ListAdapter<BaseItemViewModel<*>>() }

    override fun onAttached(binding: ActivityMainBinding) {
        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        val recyclerView = mBinding?.recyclerView
        recyclerView?.layoutManager = GridLayoutManager(mBinding?.root?.context, 2)
        recyclerView?.adapter = adapter
    }

    private fun initData() {
        adapter.add(HomeItemViewModel("深圳"))
        adapter.notifyItemInserted(0)
    }

    fun onFabClick(view: View) {

    }

}