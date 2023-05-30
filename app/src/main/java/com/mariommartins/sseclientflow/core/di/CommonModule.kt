package com.mariommartins.sseclientflow.core.di

import com.google.gson.Gson
import com.mariommartins.sseclientflow.BuildConfig
import com.mariommartins.sseclientflow.data.api.factories.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named

const val BASE_URL_NAME = "SSE_TEST_API_BASE_URL_NAME"

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    @Reusable
    fun providesGson() = Gson()

    @Provides
    @Reusable
    fun providesOkHttpClient(): OkHttpClient = HttpClientFactory().create().build()

    @Provides
    @Reusable
    @Named(BASE_URL_NAME)
    fun providesBaseUrl(): String = BuildConfig.BASE_SSE_URL
}