package com.drj.a.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.bm_input.*
import kotlinx.android.synthetic.main.web_browser.*
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.webView
import java.util.*

/**
 * Created by a on 2019. 12. 6..
 */
class WebActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_browser)

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        webView.loadUrl("http://www.google.com")


        urlText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                webView.loadUrl(urlText.text.toString())
                true
            } else {
                false
            }
        }
    }


}