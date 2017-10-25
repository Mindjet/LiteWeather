package io.github.mindjet.liteweather.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import rx.functions.Action1

/**
 * Created by Mindjet on 2017/10/25.
 */
class PopupWindowGen(private val context: Context) {

    var layoutId = 0
    var touchOutside = true
    var listeners: MutableList<Pair<Int, Action1<View>>>? = null

    fun build(): PopupWindow {
        val popupView = LayoutInflater.from(context).inflate(layoutId, null)
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.isOutsideTouchable = touchOutside
        popupWindow.isTouchable = true
        listeners?.forEach {
            val view = popupView.findViewById<View>(it.first)
            view.setOnClickListener { v -> it.second.call(v) }
        }
        return popupWindow
    }

}