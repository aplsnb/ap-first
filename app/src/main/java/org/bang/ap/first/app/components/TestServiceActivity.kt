package org.bang.ap.first.app.components

import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_service.*
import org.bang.ap.first.app.R

class TestServiceActivity : AppCompatActivity() {
    private var conn: ServiceConnection? = null

    private var myBinder: TestService2.MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_service)

//        start_service.setOnClickListener {
//            val intent = Intent(this, TestService1::class.java)
//            startService(intent)
//        }
//
//        stop_service.setOnClickListener {
//            val intent = Intent(this, TestService1::class.java)
//            stopService(intent)
//        }

//        /**
//         * 是运行一些和activity生命周期相等的后台任务，比如说跨进程通信
//         * */
//        val intent = Intent(this, TestService2::class.java)
//        conn = object : ServiceConnection {
//            // activity与service建立连接时回调该方法
//            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                Log.e("TestService2", "ServiceConnection onServiceConnected")
//                myBinder = service as TestService2.MyBinder
//            }
//
//            // activity与service断开连接时回调该方法
//            override fun onServiceDisconnected(name: ComponentName?) {
//                Log.e("TestService2", "ServiceConnection onServiceDisconnected")
//            }
//        }
//        bindService(intent, conn!!, Context.BIND_AUTO_CREATE)

        Handler().postDelayed({
//            startService(Intent(this, TestService2::class.java))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(Intent(this, TestService2::class.java));
            } else {
                startService(Intent(this, TestService2::class.java));
            }
        }, 70 * 1000)

        start_service.setOnClickListener {
            Log.e("TestService2", "getCount: ${myBinder?.getCount()}")
        }

        stop_service.setOnClickListener {
            unbindService(conn!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unbindService(conn!!)
    }
}
