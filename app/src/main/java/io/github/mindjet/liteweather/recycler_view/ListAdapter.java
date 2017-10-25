package io.github.mindjet.liteweather.recycler_view;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.github.mindjet.liteweather.BR;
import io.github.mindjet.livemvvm.viewmodel.BaseItemViewModel;

/**
 * Created by Mindjet on 2017/10/19.
 */

public class ListAdapter<T extends BaseItemViewModel> extends BaseAdapter implements List<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindVH(@Nullable BaseViewHolder holder, int position) {
        BaseItemViewModel vm = data.get(position);
        holder.getBinding().setVariable(BR.data, vm);
        holder.getBinding().executePendingBindings();
        vm.onAttachedToAdapter(holder.getBinding());
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).needLayoutId();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void swap(int from, int to) {
        Collections.swap(data, from, to);
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(@NonNull T1[] t1s) {
        return data.toArray(t1s);
    }

    @Override
    public boolean add(T t) {
        return data.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return data.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> collection) {
        return data.addAll(collection);
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends T> collection) {
        return data.addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return data.removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return data.retainAll(collection);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public T get(int i) {
        return data.get(i);
    }

    @Override
    public T set(int i, T t) {
        return data.set(i, t);
    }

    @Override
    public void add(int i, T t) {
        data.add(i, t);
    }

    @Override
    public T remove(int i) {
        return data.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator() {
        return data.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(int i) {
        return data.listIterator(i);
    }

    @NonNull
    @Override
    public List<T> subList(int i, int i1) {
        return data.subList(i, i1);
    }
}
