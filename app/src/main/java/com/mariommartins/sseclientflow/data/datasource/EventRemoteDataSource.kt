package com.mariommartins.sseclientflow.data.datasource

import com.mariommartins.sseclientflow.data.model.EventDataError
import com.mariommartins.sseclientflow.data.model.EventDataModel
import com.mariommartins.sseclientflow.domain.model.CallResult
import kotlinx.coroutines.flow.Flow

interface EventRemoteDataSource {
    suspend fun getEventFlow(): Flow<CallResult<EventDataModel, EventDataError>>
    fun clearEventFlow()
}
