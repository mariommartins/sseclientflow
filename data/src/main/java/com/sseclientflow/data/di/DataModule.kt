package com.sseclientflow.data.di

import com.sseclientflow.data.api.client.SSERequestClient
import com.sseclientflow.data.api.client.SSERequestClientImpl
import com.sseclientflow.data.datasource.EventRemoteDataSource
import com.sseclientflow.data.datasource.EventRemoteDataSourceImpl
import com.sseclientflow.data.repository.EventRepositoryImpl
import com.sseclientflow.domain.repository.EventRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

const val BASE_URL_NAME = "SSE_TEST_API_BASE_URL_NAME"

@Module
interface DataModule {
    @Binds
    fun bindSSERequestClient(
        sseRequestClient: SSERequestClientImpl
    ): SSERequestClient

    @Binds
    fun bindEventRemoteDataSource(
        eventRemoteDataSource: EventRemoteDataSourceImpl
    ): EventRemoteDataSource

    @Binds
    @Reusable
    fun bindEventRepository(
        eventRepository: EventRepositoryImpl
    ): EventRepository
}
