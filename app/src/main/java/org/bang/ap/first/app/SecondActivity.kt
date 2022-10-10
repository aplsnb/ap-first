package org.bang.ap.first.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.bang.ap.first.app.components.SecondFragment

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val extraData = intent.getStringExtra("extra_data")
//        val extraIntData = intent.getIntExtra("extra_int_data", 0)
//
//        val textView = TextView(this)
//        textView.text = "SecondActivity.${extraData}.${extraIntData}"
//        textView.gravity = Gravity.CENTER
//        textView.setOnClickListener {
//            val resultIntent = Intent()
//            resultIntent.putExtra("result_extra_string", "result_extra_string")
//            resultIntent.putExtra("result_extra_int", 1000)
//            setResult(Activity.RESULT_OK, resultIntent)
//            finish()
//        }
//        setContentView(textView)

        Log.e("SecondActivity", "onCreate")

        setContentView(R.layout.activity_second)
        val fragment = SecondFragment()
        val bundle = Bundle()
        bundle.putInt("int_extra", 100)
        bundle.putString("string_extra", "string_extra_value")
        fragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, fragment)
        ft.commit()
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
