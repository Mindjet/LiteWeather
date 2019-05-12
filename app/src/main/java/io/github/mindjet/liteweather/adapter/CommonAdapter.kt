package io.github.mindjet.liteweather.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class CommonAdapter<T>(
    private val onItemBound: (model: T, vh: CommonVH, index: Int) -> Unit,
    private var data: MutableList<T> = mutableListOf(),
    @LayoutRes private val getLayoutId: (index: Int) -> Int = { -1 },
    @LayoutRes private val layoutId: Int = -1
) : RecyclerView.Adapter<CommonVH>(), List<T> {

    override fun onCreateViewHolder(container: ViewGroup, index: Int): CommonVH {
        val firstLayoutId = getLayoutId.invoke(index)
        return if (firstLayoutId != -1) {
            mapLayout2VH(container, firstLayoutId)
        } else {
            if (layoutId != -1) {
                mapLayout2VH(container, layoutId)
            } else {
                throw RuntimeException("no layout id provided properly!")
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewHolder: CommonVH, index: Int) {
        onItemBound.invoke(data[index], viewHolder, index)
    }

    private fun mapLayout2VH(container: ViewGroup, @LayoutRes layoutId: Int): CommonVH {
        val view = LayoutInflater.from(container.context).inflate(layoutId, container, false)
        return CommonVH(view)
    }

    fun add(t: T, index: Int = -1, forceRefresh: Boolean = true) {
        if (index == -1) {
            data.add(t)
        } else {
            data.add(index, t)
        }
        if (forceRefresh) {
            notifyItemInserted(if (index == -1) itemCount - 1 else index)
        }
    }

    fun addAll(t: List<T>, index: Int = -1, forceRefresh: Boolean = true) {
        val oldSize = itemCount
        if (index == -1) {
            data.addAll(t)
        } else {
            data.addAll(index, t)
        }
        if (forceRefresh) {
            notifyItemRangeInserted(if (index == -1) oldSize - 1 else index, t.size)
        }
    }

    fun filter(condition: (t: T) -> Boolean) {
        data = data.filter(condition) as MutableList<T>
    }

    fun filterIndexed(condition: (i: Int, t: T) -> Boolean) {
        data = data.filterIndexed(condition) as MutableList<T>
    }

    fun clear(forceRefresh: Boolean = true) {
        data.clear()
        if (forceRefresh) notifyDataSetChanged()
    }

    override val size: Int
        get() = itemCount

    override fun contains(element: T) = data.any { it == element }

    override fun containsAll(elements: Collection<T>): Boolean {
        elements.forEach {
            if (!contains(it)) {
                return false
            }
        }
        return true
    }

    override fun get(index: Int) = data[index]

    override fun indexOf(element: T) = data.indexOf(element)

    override fun isEmpty() = data.isEmpty()

    override fun iterator() = data.iterator()

    override fun lastIndexOf(element: T) = data.lastIndexOf(element)

    override fun listIterator() = data.listIterator()

    override fun listIterator(index: Int) = data.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = data.subList(fromIndex, toIndex)
}