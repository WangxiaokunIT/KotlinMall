package com.example.chen.baselibrary.data.net
import com.chen.BaseLibrary.common.Constant
import com.example.chen.baselibrary.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
class RetrofitFactory private constructor() {
    companion object {
        val instance: RetrofitFactory by lazy {
            RetrofitFactory()
        }
    }

    private val retrofit: Retrofit
    private val interceptor:Interceptor

    init {
        interceptor= Interceptor {
            chain ->
            val request=chain.request()
                    .newBuilder()
                    .addHeader("Content_Type","application/json")
                    .addHeader("charset","UTF-8")
                    .addHeader("token", AppPrefsUtils.getString(Constant.KEY_SP_TOKEN))
                    .build()
            chain.proceed(request)
    }
        retrofit = Retrofit.Builder()
                .baseUrl(Constant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    private fun initClient(): OkHttpClient {

       return OkHttpClient.Builder()
               .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY;
        return interceptor;
    }
    //传api接口,泛型
    fun <T>create(service:Class<T>):T{
        return retrofit.create(service);
    }
}