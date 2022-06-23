package com.ht.githubdemo.di

import com.google.gson.GsonBuilder
import com.ht.githubdemo.network.ApiConstants
import com.ht.githubdemo.network.ApiService
import com.ht.githubdemo.network.NetworkController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ZOMATO on 22,June,2022
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideNetworkModule(apiService: ApiService) = NetworkController(apiService)

    @Provides
    fun apiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun retrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(ApiConstants.END_POINT)
        .client(httpClient)
        .build()

    @Provides
    fun okHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().setLenient().create())
}