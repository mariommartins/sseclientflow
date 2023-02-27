package com.mariommartins.sseclientflow.domain.usecase

import com.mariommartins.sseclientflow.data.repository.EventRepositoryImpl
import com.mariommartins.sseclientflow.domain.model.EventModel
import com.mariommartins.sseclientflow.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToEventFlow constructor(
    private val repository: EventRepository = EventRepositoryImpl(),
) {
    suspend fun execute(): Flow<EventModel> = repository.getEventFlow()
}
