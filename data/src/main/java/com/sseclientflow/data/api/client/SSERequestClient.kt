package com.sseclientflow.data.api.client

import com.sseclientflow.data.api.model.SSEEventState
import kotlinx.coroutines.flow.Flow

internal interface SSERequestClient {
    suspend fun connect(): Flow<SSEEventState>
    fun clearConnection()
}
