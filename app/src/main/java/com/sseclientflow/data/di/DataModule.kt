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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindSSERequestClient(
        sseRequestClient: SSERequestClientImpl
    ): SSERequestClient

    @Binds
    abstract fun bindEventRemoteDataSource(
        eventRemoteDataSource: EventRemoteDataSourceImpl
    ): EventRemoteDataSource

    @Binds
    @Reusable
    abstract fun bindEventRepository(
        eventRepository: EventRepositoryImpl
    ): EventRepository
}