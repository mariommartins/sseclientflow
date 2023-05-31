package com.mariommartins.sseclientflow.data.repository

import com.mariommartins.sseclientflow.data.datasource.EventRemoteDataSource
import com.mariommartins.sseclientflow.data.mapper.EventErrorMapper
import com.mariommartins.sseclientflow.data.mapper.EventMapper
import com.mariommartins.sseclientflow.domain.model.CallResult
import com.mariommartins.sseclientflow.domain.model.Event
import com.mariommartins.sseclientflow.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val remoteDataSource: EventRemoteDataSource,
    private val modelMapper: EventMapper,
    private val errorMapper: EventErrorMapper
) : EventRepository {
    override suspend fun getEventFlow(): Flow<Event> =
        remoteDataSource.getEventFlow().map {
            when (it) {
                is CallResult.Success -> modelMapper.map(it.value)
                is CallResult.Error -> throw errorMapper.map(it.error)
            }
        }

    override fun clearEventFlow() = remoteDataSource.clearEventFlow()
}
