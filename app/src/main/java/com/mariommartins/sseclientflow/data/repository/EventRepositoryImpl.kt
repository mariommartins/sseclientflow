package com.mariommartins.sseclientflow.data.repository

import com.mariommartins.sseclientflow.data.datasource.EventRemoteDataSource
import com.mariommartins.sseclientflow.data.datasource.EventRemoteDataSourceImpl
import com.mariommartins.sseclientflow.data.mapper.EventErrorMapper
import com.mariommartins.sseclientflow.data.mapper.EventMapper
import com.mariommartins.sseclientflow.domain.model.CallResult
import com.mariommartins.sseclientflow.domain.model.EventModel
import com.mariommartins.sseclientflow.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepositoryImpl constructor(
    private val remoteDataSource: EventRemoteDataSource = EventRemoteDataSourceImpl(),
    private val modelMapper: EventMapper = EventMapper(),
    private val errorMapper: EventErrorMapper = EventErrorMapper()
) : EventRepository {
    override suspend fun getEventFlow(): Flow<EventModel> =
        remoteDataSource.getEventFlow().map {
            when (it) {
                is CallResult.Success -> modelMapper.map(it.value)
                is CallResult.Error -> throw errorMapper.map(it.error)
            }
        }

    override fun clearEventFlow() = remoteDataSource.clearEventFlow()
}
