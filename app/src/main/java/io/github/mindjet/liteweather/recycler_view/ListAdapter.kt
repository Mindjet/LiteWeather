package io.github.mindjet.liteweather.recycler_view

import android.databinding.ViewDataBinding
import io.github.mindjet.liteweather.BR
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel

/**
 * Created by Mindjet on 2017/10/19.
 */
open class ListAdapter<B : ViewDataBinding, T : BaseItemViewModel<B>> : BaseAdapter<B>(), List<T> {

    private val data: List<T> by lazy { arrayListOf<T>() }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getLayoutId()
    }

    override fun onBindVH(holder: BaseViewHolder<B>?, position: Int) {
        val vm = data[position]
        holder?.binding?.setVariable(BR.data, vm)
        holder?.binding?.executePendingBindings()
        vm.onAttachedToAdapter(holder?.binding!!)
    }

    override val size: Int
        get() = data.size

    override fun contains(element: T): Boolean {
        return data.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return data.containsAll(elements)
    }

    override fun get(index: Int): T {
        return data[index]
    }

    override fun indexOf(element: T): Int {
        return data.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return data.iterator()
    }

    override fun lastIndexOf(element: T): Int {
        return data.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<T> {
        return data.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<T> {
        return data.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        return data.subList(fromIndex, toIndex)
    }
}