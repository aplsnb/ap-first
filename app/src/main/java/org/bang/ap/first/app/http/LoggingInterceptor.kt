package org.bang.ap.first.app.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStart = System.nanoTime()

        val request = chain.request()
        val buffer = Buffer()
        request.body?.writeTo(buffer)
        val requestBodyStr = buffer.readUtf8()
        Log.e(
            "OKHTTP",
            String.format("Sending request %s with params %s", request.url, requestBodyStr)
        )

        val response = chain.proceed(request)
        val mediaType = response.body.contentType()
        val bussinessData = response.body.string()
        val newBody = ResponseBody.create(mediaType, bussinessData)
        val newResponse = response.newBuilder().body(newBody).build()

        val timeEnd = System.nanoTime()
        Log.e(
            "OKHTTP",
            String.format(
                "Received response for %s in %.1fms >>> %s",
                request.url,
                (timeEnd - timeStart) / 1e6,
                bussinessData
            )
        )
        
        return newResponse
    }
}
