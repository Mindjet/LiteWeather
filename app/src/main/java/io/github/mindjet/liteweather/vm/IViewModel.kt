package io.github.mindjet.liteweather.vm

import android.databinding.ViewDataBinding
import android.view.View

open interface IViewModel {

    fun onBind(v: View?, binding: ViewDataBinding)

    fun initView(v: View?)

    fun loadData(v: View?)

}