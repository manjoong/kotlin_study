package com.drj.a.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.bm_input.*
import kotlinx.android.synthetic.main.web_browser.*
import org.jetbrains.anko.*
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

        registerForContextMenu(webView)
    }

    //뒤로가기 동장 허용
    override fun onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)  //menu를 나타내게 함
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.action_google, R.id.action_home -> {
                webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                webView.loadUrl("http://www.hanmail.net")
                return true
            }
            R.id.action_call -> {
//                val intent = Intent(Intent.ACTION_DIAL)
//                intent.data = Uri.parse("tel: 031-705-7847")
//                if(intent.resolveActivity(packageManager) != null){
//                    startActivity(intent)
//                }
                makeCall("010-9012-7040")
                return true
            }
            R.id.action_send_text -> {
                sendSMS("010-9012-7040", webView.url)
                return true
            }
            R.id.action_browser -> {
                email("skekf123@naver.com", "만중이가 공유하는 사이트", webView.url)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_share -> {
                share(webView.url)
            }
            R.id.action_browser -> {
                browse(webView.url)
            }
        }
        return super.onContextItemSelected(item)
    }


}