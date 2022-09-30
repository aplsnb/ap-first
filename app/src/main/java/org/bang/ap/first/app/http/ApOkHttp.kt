package org.bang.ap.first.app.http

import android.util.Log
import okhttp3.*
import okio.IOException
import java.util.concurrent.TimeUnit

object ApOkHttp {
    private val BASE_URL = "http://123.56.232.18:8080/serverdemo"

    private val client = OkHttpClient.Builder() // builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS) // 连接超时时间
        .readTimeout(10, TimeUnit.SECONDS) // 读取超时
        .writeTimeout(10, TimeUnit.SECONDS) // 写超时，也就是请求超时
        .build()

    // android分为主线程和子线程
    // 主线程就是app一启动后，android framework层会启动一个线程，主线程（ui线程）
    // 子线程 new Thread().start()

    fun get() { // 同步网络请求接口
        Thread {
            // 构造请求体
            val request = Request.Builder()
                .url("$BASE_URL/user/query?userId=1600932269")
                .build()

            // 构造请求对象
            val call = client.newCall(request)
            // 发起同步请求 execute同步执行
            val response = call.execute()

            val body = response.body.string()

            Log.e("get", "get response: $body")
        }.start()
    }

    fun getAsync() { // 异步网络请求接口
        // 构造请求体
        val request = Request.Builder()
            .url("$BASE_URL/user/query?userId=1600932269")
            .build()

        // 构造请求对象
        val call = client.newCall(request)
        // 发起异步请求 enqueue异步执行
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("getAsync", "get response onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body.string()
                Log.e("getAsync", "get response: $body")
            }
        })
    }
}
