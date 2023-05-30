package com.mariommartins.sseclientflow.data.di

import com.mariommartins.sseclientflow.data.api.client.SSERequestClient
import com.mariommartins.sseclientflow.data.api.client.SSERequestClientImpl
import com.mariommartins.sseclientflow.data.datasource.EventRemoteDataSource
import com.mariommartins.sseclientflow.data.datasource.EventRemoteDataSourceImpl
import com.mariommartins.sseclientflow.data.repository.EventRepositoryImpl
import com.mariommartins.sseclientflow.domain.repository.EventRepository
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