package com.miiladshabanii.news.data.di

import com.miiladshabanii.news.data.remote.util.NetworkResponseAdapterFactory
import com.miiladshabanii.news.data.remote.service.NewsService
import com.miiladshabanii.news.data.remote.util.SingleToArrayAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Provides
    @Singleton
    @Named("BaseUrl")
    fun baseUrlProvider(): String = "https://newsapi.org/"

    @Provides
    @Singleton
    @Named("ApiKey")
    fun apiKeyProvider(): String = "63424e03939946b5ab5003af210acac9"


    @Provides
    @Singleton
    @Named("TimeOut")
    fun timeOutProvider(): Long = 60

    @Provides
    @Singleton
    fun moshiProvider(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(SingleToArrayAdapter.INSTANCE)
            .build()


    @Provides
    @Singleton
    fun interceptor(
        @Named("ApiKey")
        apiKey: String
    ): Interceptor {
        return Interceptor { chain ->
            chain.proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", apiKey)
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    fun httpClientProvider(
        @Named("TimeOut")
        timeOut: Long,
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun retrofitProvider(
        @Named("BaseUrl")
        baseUrl: String,
        moshi: Moshi,
        client: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .client(client)
            .build()


    @Provides
    @Singleton
    fun newsServiceProvider(
        retrofit: Retrofit
    ): NewsService = retrofit.create(NewsService::class.java)


}