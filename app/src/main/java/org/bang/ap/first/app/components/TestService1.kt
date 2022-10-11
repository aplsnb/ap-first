package org.bang.ap.first.app.components

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService1 : Service() {
    private val TAG = "TestService1"

    override fun onCreate() {
        Log.e(TAG, "TestService1 onCreate")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "TestService1 onBind")
        return null
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
        Log.e(TAG, "TestService1 onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "TestService1 onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e(TAG, "TestService1 onDestroy")
        super.onDestroy()
    }
}
