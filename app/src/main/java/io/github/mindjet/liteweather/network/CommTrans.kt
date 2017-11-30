package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.model.CommonResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/11/30.
 */
object CommTrans {

    fun <T> asyncSync(): Observable.Transformer<CommonResponse<T>, T> {
        return Observable.Transformer {
            it.subscribeOn(Schedulers.io())
                    .map { it.data?.get(0) }
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> asyncSync(source: Observable<CommonResponse<T>>) {
        source.subscribeOn(Schedulers.io())
                .map { it.data?.get(0) }
                .observeOn(AndroidSchedulers.mainThread())
    }

}