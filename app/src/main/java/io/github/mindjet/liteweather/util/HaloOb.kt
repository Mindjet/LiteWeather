package io.github.mindjet.liteweather.util

import android.databinding.ObservableField

open class HaloOb<T>(default: T? = null) : ObservableField<T>() {

    var value: T? = default
        set(value) {
            field = value
            this.set(value!!)
        }

    class String(default: kotlin.String = "") : HaloOb<kotlin.String>(default)

    class Int(default: kotlin.Int = 0) : HaloOb<kotlin.Int>(default)

}