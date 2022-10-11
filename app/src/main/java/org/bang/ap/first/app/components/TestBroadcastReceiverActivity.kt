package org.bang.ap.first.app.components

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TestBroadcastReceiverActivity : AppCompatActivity() {
    private lateinit var receiver: TestBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = TestBroadcastReceiver()

        // 创建广播过滤器，指定只接受android.net.conn.CONNECTIVITY_CHANGE的广播事件
        val intentFilter = IntentFilter()
        intentFilter.addAction("org.bang.ap.first.app.components.TEST_BROADCAST_RECEIVER")
//        registerReceiver(receiver, intentFilter)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter)
        Handler().postDelayed({
            LocalBroadcastManager.getInstance(this)
                .sendBroadcast(Intent("org.bang.ap.first.app.components.TEST_BROADCAST_RECEIVER"))
        }, 1000)

//        val intent = Intent()
//        intent.action = "org.bang.ap.first.app.components.TEST_BROADCAST_RECEIVER"
//        intent.component = ComponentName(
//            packageName,
//            "org.bang.ap.first.app.components.TestBroadcastReceiver"
//        )
//        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 必须要在onDestroy时反注册，否则会内存泄漏
//        unregisterReceiver(receiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}
