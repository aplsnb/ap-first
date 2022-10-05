package org.bang.ap.first.app.http

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import org.json.JSONObject
import java.io.File
import java.util.concurrent.TimeUnit

object ApOkHttp {
    private const val BASE_URL = "http://123.56.232.18:8080/serverdemo"

    private val client: OkHttpClient

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder() // builder构造者设计模式
            .connectTimeout(10, TimeUnit.SECONDS) // 连接超时时间
            .readTimeout(10, TimeUnit.SECONDS) // 读取超时
            .writeTimeout(10, TimeUnit.SECONDS) // 写超时，也就是请求超时
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

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
            Log.e("get", "get response: ${response.body.string()}")
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
                Log.e("getAsync", "getAsync onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("getAsync", "getAsync onResponse: ${response.body.string()}")
            }
        })
    }

    /**
     * post同步请求，表单提交
     * */
    fun post() {
        val body = FormBody.Builder()
            .add("userId", "1600932269")
            .add("tagId", "71")
            .build()

        val request = Request.Builder()
            .url("$BASE_URL/tag/toggleTagFollow")
            .post(body)
            .build()

        val call = client.newCall(request)
        Thread {
            val response = call.execute()
            Log.e("post", "post response: ${response.body.string()}")
        }.start()
    }

    /**
     * post异步请求，表单提交
     * */
    fun postAsync() {
        val body = FormBody.Builder()
            .add("userId", "1600932269")
            .add("tagId", "71")
            .build()

        val request = Request.Builder()
            .url("$BASE_URL/tag/toggleTagFollow")
            .post(body)
            .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("postAsync", "postAsync onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("postAsync", "postAsync onResponse: ${response.body.string()}")
            }
        })
    }

    /**
     * post异步请求，多表单文件上传
     * */
    fun postAsyncMultipart(context: Context) {
        val file = File(Environment.getExternalStorageDirectory(), "1.png")
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT)
            return
        }

        val body = MultipartBody.Builder()
            .addFormDataPart("key", "value")
            .addFormDataPart("key1", "value2")
            .addFormDataPart(
                "file",
                "file.png",
                RequestBody.create("application/octet-stream".toMediaType(), file)
            )
            .build()

        val request = Request.Builder()
            .url("是需要支持文件上传才可以使用的")
            .post(body)
            .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("postAsyncMultipart", "postAsyncMultipart onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e(
                    "postAsyncMultipart",
                    "postAsyncMultipart onResponse: ${response.body.string()}"
                )
            }
        })
    }

    // post异步请求，提交字符串
    fun postAsyncString() {
        val textPlain = "text/plain;charset=utf-8".toMediaType()
        val textObj = "username: username; password: password"

        val applicationJson = "application/json;charset=utf-8".toMediaType()
        val jsonObj = JSONObject()
        jsonObj.put("key1", "value1")
        jsonObj.put("key2", 100)

        val body =
            RequestBody.create(textPlain, textObj)

        val request = Request.Builder()
            .url(BASE_URL)
            .post(body)
            .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("postAsyncString", "postAsyncString onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("postAsyncString", "postAsyncString onResponse: ${response.body.string()}")
            }
        })
    }
}
