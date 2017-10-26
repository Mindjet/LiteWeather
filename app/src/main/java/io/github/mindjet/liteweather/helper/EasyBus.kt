package io.github.mindjet.liteweather.helper

import io.github.mindjet.rxkit.rxbus.RxBus
import rx.Observable

/**
 * Created by Mindjet on 2017/10/27.
 */
object EasyBus {

    inline fun <reified T> subscribe(signal: String): Observable<T> {
        return RxBus.getInstance().receive(T::class.java, signal)
    }

    fun send(any: Any, signal: String) {
        RxBus.getInstance().send(any, signal)
    }

}