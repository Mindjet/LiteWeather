package io.github.mindjet.liteweather.util

import android.util.Log

class PubSub private constructor() {

    private val map = hashMapOf<String, MutableList<(o: Any) -> Unit>>()

    companion object {

        private var instance: PubSub? = null

        @Synchronized
        fun getInstance(): PubSub {
            if (instance == null) {
                instance = PubSub()
            }
            return instance as PubSub
        }
    }

    fun <T> subscribe(signal: String, callback: (t: T) -> Unit): Subscription {
        if (map.containsKey(signal)) {
            val callbacks = map[signal]
            callbacks?.add(callback as (o: Any) -> Unit)
        } else {
            val callbacks = mutableListOf(callback)
            map[signal] = callbacks as MutableList<(o: Any) -> Unit>
        }
        return Subscription(signal, callback as (o: Any) -> Unit)
    }

    fun <T> publish(signal: String, t: T) {
        map[signal]?.forEach {
            it.invoke(t as Any)
        }
    }


    inner class Subscription(private val signal: String, private val callback: (o: Any) -> Unit) {
        fun unsubscribe() {
            if (map.containsKey(signal)) {
                map[signal] = map[signal]?.filter { it == callback } as MutableList<(o: Any) -> Unit>
            }
        }
    }
}