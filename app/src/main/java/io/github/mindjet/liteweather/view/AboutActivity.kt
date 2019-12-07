package io.github.mindjet.liteweather.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import io.github.mindjet.liteweather.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        itemFeedback.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://github.com/Mindjet/LiteWeather/issues")
            startActivity(i)
        }

        tvAppVersion.text = packageManager.getPackageInfo(packageName, 0).versionName
    }


}