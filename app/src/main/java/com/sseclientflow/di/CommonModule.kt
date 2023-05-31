package com.sseclientflow.di

import com.google.gson.Gson
import com.sseclientflow.BuildConfig
import com.sseclientflow.data.api.factories.HttpClientFactory
import com.sseclientflow.data.di.BASE_URL_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    @Reusable
    fun providesGson() = Gson()

    @Provides
    fun providesOkHttpClient(): OkHttpClient = HttpClientFactory().create().build()

    @Provides
    @Reusable
    @Named(BASE_URL_NAME)
    fun providesBaseUrl(): String = BuildConfig.BASE_SSE_URL
}