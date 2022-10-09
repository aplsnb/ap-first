package org.bang.ap.first.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extraData = intent.getStringExtra("extra_data")
        val extraIntData = intent.getIntExtra("extra_int_data", 0)

        val textView = TextView(this)
        textView.text = "SecondActivity.${extraData}.${extraIntData}"
        textView.gravity = Gravity.CENTER
        textView.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result_extra_string", "result_extra_string")
            resultIntent.putExtra("result_extra_int", 1000)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        setContentView(textView)

        Log.e("SecondActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.e("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("SecondActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        Log.e("SecondActivity", "onRestart")
    }
}
