package io.github.mindjet.liteweather.recycler_view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * Created by Mindjet on 2017/10/19.
 */
class BaseViewHolder<out B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)