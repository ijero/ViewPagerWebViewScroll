package cn.ijero.viewpagerdemo

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView

/**
 * Created by Jero on 2017/11/15.
 */
class MyWebView
@JvmOverloads
constructor(ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : WebView(ctx, attrs, defStyleAttr) {
    var onScrollListener: OnScrollListener? = null
    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        onScrollListener?.onScrolled(true)
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        if (event.action == MotionEvent.ACTION_DOWN) {
            onScrollListener?.onScrolled(false)
        }
        return true
    }


    interface OnScrollListener {
        fun onScrolled(canScroll: Boolean)
    }

}