package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.BuildConfig
import com.fleenmobile.androidinterviewtask.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun okHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

    @Singleton
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    @Singleton
    @Provides
    fun retrofit(httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.GODT_BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    fun dailiesService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
}