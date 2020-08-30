package com.github.cr9ck.offerwall.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.cr9ck.offerwall.R
import kotlinx.android.synthetic.main.fragment_web_view.*

@SuppressLint("SetJavaScriptEnabled")
class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private val args: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    private fun initWebView() {
        CookieManager.getInstance().setAcceptCookie(true)
        webView.apply {
            settings.javaScriptEnabled = true
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    loadUrl(request?.url.toString())
                    return true
                }
            }
            loadUrl(args.url)
            setOnKeyListener { view, i, keyEvent ->

                if (i == KeyEvent.KEYCODE_BACK &&
                    keyEvent.action == MotionEvent.ACTION_UP &&
                    canGoBack() ) {
                    goBack()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
}