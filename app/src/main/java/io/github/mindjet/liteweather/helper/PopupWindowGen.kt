package io.github.mindjet.liteweather.helper

import android.content.Context
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import io.github.mindjet.liteweather.R

/**
 * Created by Mindjet on 2017/10/25.
 */
class PopupWindowGen private constructor(private val context: Context) {

    companion object {
        fun with(context: Context?, block: PopupWindowGen.() -> PopupWindow): PopupWindow {
            return with(PopupWindowGen(context!!)) {
                block()
            }
        }
    }

    var layoutId = 0
    var touchOutside = true
    var width = 100
    var elevation = 10f
    var listeners: MutableList<Pair<Int, (View) -> Unit>> = mutableListOf()

    fun addListener(@IdRes id: Int, callback: (View) -> Unit) = listeners.add(Pair(id, callback))

    fun build(): PopupWindow {
        val popupView = LayoutInflater.from(context).inflate(layoutId, null)
        val popupWindow = PopupWindow(popupView, width.dp2px(), ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.setBackgroundDrawable(context.resources.getDrawable(android.R.color.white))     //加了这句才能点击其他区域自动关闭
        popupWindow.isOutsideTouchable = touchOutside
        popupWindow.isTouchable = true
        popupWindow.elevation = elevation
        listeners.forEach {
            val view = popupView.findViewById<View>(it.first)
            view.setOnClickListener { v -> it.second(v) }
        }
        return popupWindow
    }

}