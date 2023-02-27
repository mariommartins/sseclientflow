package com.mariommartins.sseclientflow.data.api.client

import com.mariommartins.sseclientflow.data.api.model.SSEEventState
import kotlinx.coroutines.flow.Flow

interface SSERequestClient {
    suspend fun connect(): Flow<SSEEventState>
    fun clearConnection()
}
