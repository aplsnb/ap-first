package org.bang.ap.first.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.bang.ap.first.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        println("where is me ?")

//        R.id.text_home.onClick(this) {
//            println("text_home onClick")
//        }

//        setContentView(R.layout.comps_material_button)
//        val toggleGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggle_group)
//        toggleGroup.addOnButtonCheckedListener { _, _, _ ->
//            Log.e("MainActivity", "isChecked")
//        }

//        ApOkHttp.get()
//        ApOkHttp.getAsync()
//        ApOkHttp.post()
//        ApOkHttp.postAsync()
//        ApOkHttp.postAsyncMultipart(this)
//        ApOkHttp.postAsyncString()

//        // onFailure和onResponse的回调都是在主线程的
//        val apiService = ApRetrofit.create(ApiService::class.java)
//        apiService.queryUser("1600932269").enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Log.e("queryUser", "queryUser onResponse: ${response.body() ?: "response is null"}")
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.e("queryUser", "queryUser onFailure: ${t.message ?: "unknown reason"}")
//            }
//        })

        Log.e("MainActivity", "onCreate")

        textView = TextView(this)
        textView.text = "MainActivity"
        textView.gravity = Gravity.CENTER
        textView.setOnClickListener {
//            // 显式intent
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", "extra_data")
//            intent.putExtra("extra_int_data", 100)
//            startActivityForResult(intent, 200)

//            // 隐式intent
//            val intent = Intent()
//            intent.action = "org.bang.ap.first.app.SECONDACTIVITY"
//            intent.addCategory("org.bang.ap.first.app.category.SECONDACTIVITY")
//            intent.putExtra("extra_data", "extra_data")
//            intent.putExtra("extra_int_data", 100)
//            startActivityForResult(intent, 200)

//            // 给10086打电话
//            val uri = Uri.parse("tel:10086")
//            val intent = Intent(Intent.ACTION_DIAL, uri)
//            startActivity(intent)

            // 给10086发短信
            val uri = Uri.parse("smsto:10086")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello")
            startActivity(intent)
        }
        setContentView(textView)
    }

    override fun onStart() {
        super.onStart()

        Log.e("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("MainActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        Log.e("MainActivity", "onRestart")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200 && resultCode == Activity.RESULT_OK && data != null) {
            val stringExtraResult = data.getStringExtra("result_extra_string")
            val intExtraResult = data.getIntExtra("result_extra_int", 0)

            textView.text = "MainActivity.${stringExtraResult}.${intExtraResult}"
        }
    }
}

//fun Int.onClick(activity: Activity, click: () -> Unit) {
//    activity.findViewById<View>(this).setOnClickListener {
//        println("onClick")
//        click()
//    }
//}
