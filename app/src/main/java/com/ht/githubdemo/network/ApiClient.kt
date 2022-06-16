package com.ht.githubdemo.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * Created by ZOMATO on 15,June,2022
 */
object ApiClient {
    private val httpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    private val retrofit  = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl("https://api.github.com/")
        .client(httpClient)
        .build()

    val service: ApiService = retrofit.create(ApiService::class.java)

    internal class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
            val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter { body -> if (body.contentLength().toInt() == 0) null else delegate.convert(body) }
        }
    }
}