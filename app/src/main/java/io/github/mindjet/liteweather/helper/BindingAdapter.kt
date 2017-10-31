package io.github.mindjet.liteweather.helper

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by Mindjet on 2017/10/31.
 */

@BindingAdapter("visible")
fun setVisiblity(view: View, visible: Boolean) {
    if (visible) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}