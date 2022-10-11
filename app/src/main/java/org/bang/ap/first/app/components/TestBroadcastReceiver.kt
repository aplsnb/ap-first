package org.bang.ap.first.app.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class TestBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action ?: return
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if (info != null && info.isAvailable) {
                val typeName = info.typeName
                Toast.makeText(context, "当前网络连接类型: $typeName", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "当前无网络连接", Toast.LENGTH_LONG).show()
            }
        } else if (action == "org.bang.ap.first.app.components.TEST_BROADCAST_RECEIVER") {
            Toast.makeText(context, "静态注册广播，接受到了应用自定义的事件", Toast.LENGTH_LONG).show()
        }
    }
}
