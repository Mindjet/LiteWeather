package io.github.mindjet.liteweather.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import io.github.mindjet.liteweather.R

class LoadingDialog(ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }

}