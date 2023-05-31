package com.sseclientflow.data.datasource

import com.sseclientflow.data.model.EventResponse
import com.sseclientflow.data.model.EventResponseError
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    suspend fun getEventFlow(): Flow<com.sseclientflow.domain.model.CallResult<EventResponse, EventResponseError>>
    fun clearEventFlow()
}
