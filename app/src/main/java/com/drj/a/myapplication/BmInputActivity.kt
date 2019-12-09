package com.drj.a.myapplication

/**
 * Created by a on 2019. 11. 25..
 */
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.bm_input.*
import org.jetbrains.anko.startActivity

class BmInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bm_input)

        loadData()

        result.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java )

            saveData(tallEditText.text.toString().toInt(), weightEditText.text.toString().toInt())


            startActivity<ResultActivity>(
                    "weight" to weightEditText.text.toString(),
                    "tall" to tallEditText.text.toString()
            )

        }

    }

    private fun saveData(tall: Int, weight: Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_TALL", tall)
                .putInt("KEY_WEIGHT", weight)
                .apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val tall = pref.getInt("KEY_TALL", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)


        if (tall != 0 && weight != 0){
            tallEditText.setText(tall.toString())
            weightEditText.setText(weight.toString())
        }
    }
}