package cn.ijero.viewpagerdemo

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Jero on 2017/11/15.
 */
class MyViewPager
@JvmOverloads
constructor(ctx: Context, attrs: AttributeSet? = null) : ViewPager(ctx, attrs) {
    private var isPagingEnabled = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return  this.isPagingEnabled &&  super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev)
    }

    override fun canScroll(v: View?, checkV: Boolean, dx: Int, x: Int, y: Int): Boolean {
        return this.isPagingEnabled && super.canScroll(v, checkV, dx, x, y)
    }

    fun setPagingEnabled(b: Boolean) {
        this.isPagingEnabled = b
    }

    fun getPagingStatus(): Boolean? {
        return isPagingEnabled
    }
}