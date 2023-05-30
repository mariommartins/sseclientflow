package com.mariommartins.sseclientflow.data.datasource

import com.mariommartins.sseclientflow.data.model.EventResponse
import com.mariommartins.sseclientflow.data.model.EventResponseError
import com.mariommartins.sseclientflow.domain.model.CallResult
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    suspend fun getEventFlow(): Flow<CallResult<EventResponse, EventResponseError>>
    fun clearEventFlow()
}
