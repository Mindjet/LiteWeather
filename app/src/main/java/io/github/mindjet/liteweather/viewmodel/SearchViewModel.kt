package io.github.mindjet.liteweather.viewmodel

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import io.github.mindjet.liteweather.databinding.ActivitySearchBinding
import io.github.mindjet.liteweather.helper.finishWithFade
import io.github.mindjet.liteweather.helper.set
import io.github.mindjet.liteweather.helper.toast
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/10/23.
 */
class SearchViewModel : BaseViewModel<ActivitySearchBinding>() {

    private val editText: EditText by lazy { binding?.editText!! }

    override fun onAttached(binding: ActivitySearchBinding) {
        editText.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) onSearch()
            false
        }
    }

    private fun onSearch() {
        toast("${editText.text}")
    }

    fun onBack(view: View) {
        finishWithFade()
    }

    fun onClear(view: View) {
        editText set ""
    }

    override fun onBackPressed(): Boolean {
        finishWithFade()
        return true
    }

}