package io.github.mindjet.liteweather.viewmodel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.ViewAnimationUtils
import io.github.mindjet.liteweather.consant.Constant
import io.github.mindjet.liteweather.databinding.ActivityMainBinding
import io.github.mindjet.liteweather.helper.EasyBus
import io.github.mindjet.liteweather.helper.startWithFade
import io.github.mindjet.liteweather.recycler_view.ListAdapter
import io.github.mindjet.liteweather.view.SearchActivity
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/17.
 */
class MainViewModel : BaseViewModel<ActivityMainBinding>() {

    private val adapter by lazy { ListAdapter<BaseItemViewModel<*>>() }

    private val revealMask by lazy { binding?.revealMask!! }

    private var fabX = 0
    private var fabY = 0
    private var revealRadius = 0f

    override fun onAttached(binding: ActivityMainBinding) {
        initRecyclerView()
        initData()
        initReceive()
    }

    private fun initRecyclerView() {
        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        recyclerView?.adapter = adapter
        ItemTouchHelper(DragItemTouchHelperCallback()).attachToRecyclerView(recyclerView)
    }

    private fun initData() {
        adapter.add(WeatherItemViewModel("九寨沟"))
        adapter.add(WeatherItemViewModel("沈阳"))
        adapter.add(WeatherItemViewModel("北京"))
        adapter.add(WeatherItemViewModel("三亚"))
        adapter.notifyItemRangeInserted(0, 4)
    }

    private fun initReceive() {
        EasyBus.subscribe<WeatherItemViewModel>(Constant.SIGNAL_DELETE_ITEM)
                .subscribe {
                    val pos = adapter.indexOf(it)
                    adapter.removeAt(pos)
                    adapter.notifyItemRemoved(pos)
                }
    }

    fun onFabClick(view: View) {
        view.isClickable = false
        if (fabX == 0 && fabY == 0) {
            val pos = IntArray(2); view.getLocationOnScreen(pos)
            fabX = pos[0] + view.width / 2; fabY = pos[1] + view.height / 2
            revealRadius = Math.sqrt(Math.pow(fabX.toDouble(), 2.toDouble()) + Math.pow(fabY.toDouble(), 2.toDouble())).toFloat()
        }
        val va = ViewAnimationUtils.createCircularReveal(revealMask, fabX, fabY, 0f, revealRadius)
        va.duration = 400
        revealMask.visibility = View.VISIBLE
        va.start()
        view.postDelayed({ startWithFade<SearchActivity>(); view.isClickable = true }, 320)
    }

    override fun onResume() {
        if (!revealMask.isAttachedToWindow) return
        val va = ViewAnimationUtils.createCircularReveal(revealMask, fabX, fabY, revealRadius, 0f)
        va.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                revealMask.visibility = View.GONE
            }
        })
        va.duration = 400
        va.start()
    }

    private inner class DragItemTouchHelperCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(dragFlags, 0)
        }

        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            val from = viewHolder?.adapterPosition
            val to = target?.adapterPosition
            adapter.swap(from!!, to!!)
            adapter.notifyItemMoved(from, to)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

        }
    }


}