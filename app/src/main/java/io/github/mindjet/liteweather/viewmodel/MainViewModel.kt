package io.github.mindjet.liteweather.viewmodel

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import io.github.mindjet.library.extension.log
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.consant.Constant
import io.github.mindjet.liteweather.databinding.ActivityMainBinding
import io.github.mindjet.liteweather.helper.EasyBus
import io.github.mindjet.liteweather.recycler_view.ListAdapter
import io.github.mindjet.liteweather.view.SearchActivity
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/17.
 */
class MainViewModel : BaseViewModel<ActivityMainBinding>() {

    private val adapter by lazy { ListAdapter<BaseItemViewModel<*>>() }
    private val revealLayout by lazy { binding?.revealLayout }

    override fun onAttached(binding: ActivityMainBinding) {
        initRecyclerView()
        initData()
        initReceive()
        //Todo 包装在 RevealLayout内部并且移除该监听器
        revealLayout?.viewTreeObserver?.addOnWindowFocusChangeListener { log("on reveal layout changed"); initRevealLayout() }
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

    private fun initRevealLayout() {
        with(revealLayout!!) {
            fromTo(activity!!, SearchActivity::class.java)
            fabIcon = R.mipmap.ic_add
            fabColor = context.resources.getColor(R.color.colorSunny)
            revealMaskColor = context.resources.getColor(android.R.color.white)
            notifyChanged()
        }
    }

    override fun onResume() {
        revealLayout?.backToActivity()
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