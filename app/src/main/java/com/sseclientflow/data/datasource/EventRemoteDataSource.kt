package com.sseclientflow.data.datasource

import com.sseclientflow.data.model.EventResponse
import com.sseclientflow.data.model.EventResponseError
import com.sseclientflow.domain.model.CallResult
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    suspend fun getEventFlow(): Flow<CallResult<EventResponse, EventResponseError>>
    fun clearEventFlow()
}
