package com.drj.a.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.stop_watch.*
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

/**
 * Created by a on 2019. 12. 4..
 */
class StopWatchActivity : AppCompatActivity() {

    private var time = 0
    private var runState =false
    private var lab = 1
    private var timerTask: Timer? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stop_watch)


        playbutton.setOnClickListener{
            runState = !runState
            if(runState == true){
                start()
            }else{
                pause()
            }
        }

        labbutton.setOnClickListener {
            labrecord()
        }

        refrashbutton.setOnClickListener{
            refrash()
        }

    }




    private fun start(){
        playbutton.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period = 10){
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread{
                secText.text = "$sec"
                miliText.text = "$milli"
            }
        }
    }
    private fun pause(){
        playbutton.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    private fun labrecord(){
        var labtime = this.time
        var textView = TextView(this)
        textView.text = "${lab}번째 기록 : ${labtime/100}.${labtime%100}"

        labLayout.addView(textView, 0)
        lab++
    }

    private fun refrash(){
        time = 0
        runState = false
        playbutton.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secText.text="0"
        miliText.text="00"
    }





}