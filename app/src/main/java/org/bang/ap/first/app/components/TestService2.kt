package org.bang.ap.first.app.components

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log

class TestService2 : Service() {
    private val TAG = "TestService2"

    private var count = 0

    private var quit = false

    // 定义onBinder方法返回的对象
    private val binder: MyBinder = MyBinder()

    inner class MyBinder : Binder() {
        fun getCount(): Int {
            return count
        }
    }

    // service被创建时调用
    override fun onCreate() {
        Log.e(TAG, "TestService2 onCreate")
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = Notification.Builder(applicationContext, "channel_id")
                .build()
            startForeground(1, notification)
        }

        Thread {
            while (true) {
                if (quit) {
                    break
                }
                Thread.sleep(1000)
                count++
            }
        }.start()
    }

    // 必须实现的方法，绑定该service时回调该方法
    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "TestService2 onBind")
        return binder
    }

    /**
     * 对于使用startService的方式而言，onStartCommand就是我们用于做后台任务的地方
     * 如果我们多次调用startService，会直接回调onStartCommand，而不再回调onCreate
     *
     * 这种方式启动的服务，它的生命周期和应用程序的生命周期一样长，只要应用程序不被杀死，服务就会一直运行，
     * 除非我们调用stopService
     *
     * startService一般来说是用于创建一个长时间持续运行的后台任务才会使用，比如socket或file upload download
     * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "TestService2 onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "TestService2 onUnbind")

        quit = true

        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e(TAG, "TestService2 onDestroy")
        super.onDestroy()
    }
}
