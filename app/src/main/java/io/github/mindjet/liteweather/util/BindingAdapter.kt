package io.github.mindjet.liteweather.util

import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.ImageView

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    Log.e("sss", "setting view: $this")
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("backgroundResource")
fun View.setBackgroundRes(br: Int) {
    this.setBackgroundResource(br)
}

@BindingAdapter("imgUrl")
fun ImageView.setImageUrl(url: String) {
    Log.e("sss", "setting image :$this $url")
    this.load(url)  //already implemented in extension
}