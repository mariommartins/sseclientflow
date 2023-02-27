package com.mariommartins.sseclientflow.domain.repository

import com.mariommartins.sseclientflow.domain.model.EventModel
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEventFlow(): Flow<EventModel>
    fun clearEventFlow()
}
