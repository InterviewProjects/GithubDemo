package com.ht.githubdemo.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Created by ZOMATO on 15,June,2022
 */
@JvmSuppressWildcards
interface ApiService {
    @GET
    suspend fun <T> get(@Url url: String, @QueryMap queryMap: Map<String, Any> = HashMap()): Response<T>
}