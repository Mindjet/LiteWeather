package io.github.mindjet.liteweather.viewmodel

import android.databinding.ObservableField
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import io.github.mindjet.liteweather.databinding.ActivitySearchBinding
import io.github.mindjet.liteweather.helper.set
import io.github.mindjet.liteweather.helper.toast
import io.github.mindjet.liteweather.model.SearchResponse
import io.github.mindjet.liteweather.network.RetrofitInstance
import io.github.mindjet.liteweather.network.WeatherService
import io.github.mindjet.liteweather.network.handlerException
import io.github.mindjet.liteweather.view.custom.RevealLayout
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mindjet on 2017/10/23.
 */
class SearchViewModel : BaseViewModel<ActivitySearchBinding>() {

    private val editText: EditText by lazy { binding?.editText!! }

    val resultVisible by lazy { ObservableField(false) }
    val loadingVisible by lazy { ObservableField(false) }
    val resultLocation by lazy { ObservableField("--") }

    override fun onAttached(binding: ActivitySearchBinding) {
        editText.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) onSearch()
            false
        }
    }

    private fun onSearch() {
        RetrofitInstance.get<WeatherService>()
                .searchCity(editText.text.toString())
                .doOnSubscribe { resultVisible.set(false); loadingVisible.set(true) }
                .subscribeOn(Schedulers.io())
                .map { it.data[0] }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ updateData(it) }, { handlerException(it); loadingVisible.set(false) })
    }

    private fun updateData(response: SearchResponse) {
        if (response.status == "unknown city") {
            toast("没有找到该城市")
            return
        }
        resultLocation.set(response.basic.country)
        resultVisible.set(true); loadingVisible.set(false)
    }

    fun onBack(view: View) {
        onBackPressed()
    }

    fun onClear(view: View) {
        editText set ""
    }

    override fun onBackPressed(): Boolean {
        RevealLayout.concealFinish(activity!!)
        return true
    }

}