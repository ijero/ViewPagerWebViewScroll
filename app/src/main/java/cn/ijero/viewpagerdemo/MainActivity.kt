package cn.ijero.viewpagerdemo

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyWebView.OnScrollListener {
    override fun onScrolled(canScroll: Boolean) {
        vp.setPagingEnabled(canScroll)
    }

    private val webViewPager = WebViewPager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp.apply {
            adapter = webViewPager
        }

        val view = LayoutInflater.from(this).inflate(R.layout.layout_main, null)
        webViewPager.addView(view)

        val webView = MyWebView(this).apply {
            onScrollListener = this@MainActivity
            webViewClient = object : WebViewClient() {
                var isUpdateHistory = false
                override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                    isUpdateHistory = true
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return isUpdateHistory
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    isUpdateHistory = false
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }
            }
            webChromeClient = object : WebChromeClient() {
            }
            settings.javaScriptEnabled = true
            loadUrl("http://www.baidu.com")

        }
        webViewPager.addView(webView)

    }

    class WebViewPager : PagerAdapter() {
        var pages = arrayListOf<View>()

        override fun isViewFromObject(view: View?, `object`: Any?) = `object` == view

        override fun getCount() = pages.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = pages[position]
            container.addView(view)
            return view
        }

        fun addView(view: View) {
            pages.add(view)
            notifyDataSetChanged()
        }

        fun removeAtIndex(index: Int) {
            pages.removeAt(index)
            notifyDataSetChanged()
        }

    }
}
