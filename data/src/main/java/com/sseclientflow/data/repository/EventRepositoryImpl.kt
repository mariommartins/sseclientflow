package com.sseclientflow.data.repository

import com.sseclientflow.data.datasource.EventRemoteDataSource
import com.sseclientflow.data.mapper.EventErrorMapper
import com.sseclientflow.data.mapper.EventMapper
import com.sseclientflow.domain.model.CallResult
import com.sseclientflow.domain.model.Event
import com.sseclientflow.domain.repository.EventRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
