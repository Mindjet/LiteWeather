package io.github.mindjet.liteweather.network

import io.github.mindjet.liteweather.helper.toast
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Mindjet on 2017/10/31.
 */
fun handlerException(t: Throwable) {
    val exception = when (t) {
        is UnknownHostException -> "无法连接到服务器，请检查网络连接"
        is SocketTimeoutException -> "连接服务器超时，请重试"
        else -> t.message
    }
    toast(exception!!)
}