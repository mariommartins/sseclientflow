package com.sseclientflow.domain.repository

import com.sseclientflow.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEventFlow(): Flow<Event>
    fun clearEventFlow()
}
