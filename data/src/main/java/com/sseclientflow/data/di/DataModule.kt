package com.sseclientflow.data.di

import com.google.gson.Gson
import com.sseclientflow.data.api.client.SSERequestClient
import com.sseclientflow.data.api.client.SSERequestClientImpl
import com.sseclientflow.data.api.factory.HttpClientFactory
import com.sseclientflow.data.datasource.EventRemoteDataSource
import com.sseclientflow.data.datasource.EventRemoteDataSourceImpl
import com.sseclientflow.data.repository.EventRepositoryImpl
import com.sseclientflow.domain.repository.EventRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient

@Module
abstract class DataModule {
    @Binds
    internal abstract fun bindSSERequestClient(
        sseRequestClient: SSERequestClientImpl
    ): SSERequestClient

    @Binds
    internal abstract fun bindEventRemoteDataSource(
        eventRemoteDataSource: EventRemoteDataSourceImpl
    ): EventRemoteDataSource

    @Binds
    @Reusable
    internal abstract fun bindEventRepository(
        eventRepository: EventRepositoryImpl
    ): EventRepository

    companion object {
        @Provides
        @Reusable
        internal fun providesGson() = Gson()

        @Provides
        internal fun providesOkHttpClient(): OkHttpClient = HttpClientFactory().create().build()
    }
}
