package io.github.mindjet.liteweather.viewmodel

import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import io.github.mindjet.liteweather.R
import io.github.mindjet.liteweather.consant.Constant
import io.github.mindjet.liteweather.consant.WeatherTxt
import io.github.mindjet.liteweather.databinding.ActivityDetailBinding
import io.github.mindjet.livemvvm.viewmodel.BaseViewModel

/**
 * Created by Mindjet on 2017/11/11.
 */
class DetailViewModel : BaseViewModel<ActivityDetailBinding>() {

    private val city by lazy { activity?.intent?.getStringExtra(Constant.INTENT_CITYNAME) }
    private val condition by lazy { activity?.intent?.getStringExtra(Constant.INTENT_CONDITION) }

    private val recyclerView by lazy { binding?.recyclerView }
    private val collapsingLayout by lazy { binding?.collapsingLayout }
    private val toolbar by lazy { binding?.toolbar }

    val cityName by lazy { ObservableField("--") }
    val background by lazy { ObservableField<Drawable>() }

    override fun onAttached(binding: ActivityDetailBinding) {
        initCollapsingLayout()
        updateBackground()
    }

    private fun initCollapsingLayout() {
        collapsingLayout?.title = city
        collapsingLayout?.setCollapsedTitleTextAppearance(R.style.TextAppearance)
        collapsingLayout?.setExpandedTitleTextAppearance(R.style.TextAppearance_Expend)
        toolbar?.setNavigationOnClickListener { onBack() }
    }

    private fun updateBackground() {
        val drawableId = WeatherTxt.getCorrespondingBackground(condition!!)
        background.set(context?.resources?.getDrawable(drawableId))
    }

    private fun fetchData() {

    }

    fun onBack() {
        activity?.onBackPressed()
    }

}