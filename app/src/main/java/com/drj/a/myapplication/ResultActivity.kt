package com.drj.a.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.drj.a.myapplication.R
import kotlinx.android.synthetic.main.bm_input.*
import kotlinx.android.synthetic.main.bm_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bm_result)

        val tall_result = intent.getStringExtra("tall").toInt()
        val weight_result = intent.getStringExtra("weight").toInt()

        val bmi = weight_result / Math.pow(tall_result / 100.0, 2.0)

        when{
            bmi >= 35 -> bm_result.text = "고도 비만"
            bmi >= 25 -> bm_result.text = "과체중"
            bmi >= 18.5 -> bm_result.text = "정상"
            else -> bm_result.text = "저체중"

        }

        when{
            bmi >= 25 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            else -> imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)

        }
        toast("$bmi")

    }
}