package com.mariommartins.sseclientflow.domain.repository

import com.mariommartins.sseclientflow.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEventFlow(): Flow<Event>
    fun clearEventFlow()
}
