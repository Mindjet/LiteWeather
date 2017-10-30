package io.github.mindjet.liteweather.view.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.FrameLayout
import java.lang.ref.WeakReference

/**
 * Created by Mindjet on 2017/10/27.
 */
class RevealLayout : FrameLayout {

    companion object {
        fun concealFinish(activity: Activity) {
            activity.finish()
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs()
    }

    private var from: WeakReference<Activity>? = null
    private var to: WeakReference<Class<*>>? = null

    private val fab by lazy { FloatingActionButton(context) }
    private val revealMask by lazy { View(context) }

    private var centerX = 0
    private var centerY = 0
    private var revealRadius = 0

    var fabColor: Int = Color.BLACK
    var fabIcon: Int = android.R.drawable.ic_delete
    var fabGravity = Gravity.BOTTOM or Gravity.END
    var fabMargin: Int = 16
    var revealMaskColor: Int = Color.BLACK

    var revealDuration = 400L
    var concealDuration = 400L

    private fun initAttrs() {

    }

    fun <T> fromTo(activity: Activity, clazz: Class<T>) {
        from = WeakReference(activity)
        to = WeakReference(clazz)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addFab()
        addRevealMask()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        if (revealRadius == 0) calParams()
    }

    private fun addFab() {
        addView(fab)
        updateFabStyle()
        with(fab) {
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            setOnClickListener { onFabClick(it) }
        }
    }

    private fun addRevealMask() {
        addView(revealMask)
        updateRevealMaskStyle()
        with(revealMask) {
            visibility = View.GONE
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    private fun updateFabStyle() {
        val lp = FrameLayout.LayoutParams(layoutParams)
        val px = fabMargin.dp2px()
        lp.gravity = fabGravity
        lp.setMargins(px, px, px, px)
        fab.layoutParams = lp
        fab.setImageResource(fabIcon)
        fab.backgroundTintList = ColorStateList.valueOf(fabColor)
    }

    private fun updateRevealMaskStyle() {
        revealMask.background = ColorDrawable(revealMaskColor)
    }

    private fun calParams() {
        val parentPos = IntArray(2)
        this.getLocationOnScreen(parentPos)
        val fabPos = IntArray(2)
        fab.getLocationOnScreen(fabPos)
        centerX = fabPos[0] - parentPos[0] + fab.width / 2
        centerY = fabPos[1] - parentPos[1] + fab.height / 2
        revealRadius = Math.sqrt((width pow 2) + (height pow 2)).toInt()
    }


    private fun onFabClick(view: View) {
        fab.visibility = View.GONE
        revealMask.visibility = View.VISIBLE
        val va = ViewAnimationUtils.createCircularReveal(revealMask, centerX, centerY, 0f, revealRadius.toFloat())
        va.duration = revealDuration
        va.start()
        view.postDelayed(this::goToActivity, revealDuration - 50)
    }

    private fun goToActivity() {
        val from = from?.get()
        val to = to?.get()
        if (from == null) throw KotlinNullPointerException("RevealLayout: You have NOT assigned the activity which the reveal animation start from!")
        if (to == null) throw KotlinNullPointerException("RevealLayout: You have NOT assigned the activity which the reveal animation go to!")
        from.startActivity(Intent(from, to))
        from.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun concealBack() {
        if (!isAttachedToWindow) return
        val va = ViewAnimationUtils.createCircularReveal(revealMask, centerX, centerY, revealRadius.toFloat(), 0f)
        va.duration = concealDuration
        fab.visibility = View.VISIBLE
        va.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                revealMask.visibility = View.GONE
            }
        })
        va.start()
    }

    fun notifyChanged() {
        updateFabStyle()
        updateRevealMaskStyle()
        calParams()
    }

    private fun Int.dp2px(): Int {
        val density = context.resources.displayMetrics.density
        return (this * density + 0.5f).toInt()
    }

    private infix fun Int.pow(pow: Int): Double {
        return Math.pow(this.toDouble(), pow.toDouble())
    }

}