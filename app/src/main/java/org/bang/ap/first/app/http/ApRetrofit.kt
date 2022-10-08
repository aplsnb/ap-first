package org.bang.ap.first.app.http

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

object ApRetrofit {
    private const val BASE_URL = "http://123.56.232.18:8080/serverdemo/"

    private const val BASE_URL2 = "https://www.songyubao.com/"

    private val client = OkHttpClient.Builder() // builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS) // 连接超时时间
        .readTimeout(10, TimeUnit.SECONDS) // 读取超时
        .writeTimeout(10, TimeUnit.SECONDS) // 写超时，也就是请求超时
        .addInterceptor(LoggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create()) // 数据转换适配器
        .build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}

interface ApiService {
    @GET("user/query")
    fun queryUser(@Query(value = "userId", encoded = true) userId: String): Call<String>

    // 使用@Headers添加多个请求头
    @Headers("User-Agent: android", "apikey: 123456789")
    @GET("user/query")
    fun queryUser2(@Query(value = "userId", encoded = true) userId: String): Call<String>

    // 多个参数的情况下可以使用@QueryMap，但只能用在GET请求上
    @GET("user/query")
    fun queryUser3(@QueryMap params: Map<String, String>): Call<String>

    /**
     * 很多情况下，我们需要上传json格式的数据。当我们注册新用户的时候，因为用户注册时数据相对较多
     * 并可能以后会变化，这时候，服务端可能要求我们上传json格式的数据。此时就要用@Body注解来实现
     * 直接传入实体，它会自行转换成json，@Body只能用在POST请求上
     *
     * 字符串提交
     * */
    @POST("user/update")
    fun update(@Body post: News): Call<String>

    /**
     *表单提交（键值对提交）
     * */
    @POST
    @FormUrlEncoded
    fun executePost(@FieldMap map: Map<String, Any>): Call<String>

    /**
     * 表单上传文件（键值对提交、同时上传文件）
     * */
    @POST("upload/upload")
    @Multipart
    fun register(
        @Field(value = "openId", encoded = true) openId: String,
        @PartMap map: Map<String, MultipartBody.Part>
    ): Call<String>

    @GET("static/book/assets/study.json")
    fun getStudy(): Call<List<Course>>
}

class News

data class Course(
    val label: String,
    val poster: String,
    var progress: String,
    val title: String
)
