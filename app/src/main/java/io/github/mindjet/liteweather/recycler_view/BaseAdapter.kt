package io.github.mindjet.liteweather.recycler_view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Mindjet on 2017/10/18.
 */
abstract class BaseAdapter<B : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<B>>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<B> {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent?.context), viewType, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<B>?, position: Int) {
        onBindVH(holder, position)
    }

    abstract fun onBindVH(holder: BaseViewHolder<B>?, position: Int)

}