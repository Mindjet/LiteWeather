package io.github.mindjet.liteweather.util

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import android.widget.ImageView

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("backgroundResource")
fun View.setBackgroundRes(br: Int) {
    this.setBackgroundResource(br)
}

@BindingAdapter("imgUrl")
fun ImageView.setImageUrl(url: String?) {
    this.load(url)  //already implemented in extension
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.setIsRefresh(isRefresh: Boolean) {
    this.isRefreshing = isRefresh
    Log.e("tt", "set is refresh: $isRefresh")
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(listener: () -> Unit) {
    this.setOnRefreshListener { listener.invoke() }
}