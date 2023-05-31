package com.mariommartins.sseclientflow.domain.usecase

import com.mariommartins.sseclientflow.domain.model.Event
import com.mariommartins.sseclientflow.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SubscribeToEventFlow {
    suspend operator fun invoke(): Flow<Event>
}

class SubscribeToEventFlowImpl @Inject constructor(
    private val eventRepository: EventRepository
) : SubscribeToEventFlow {
    override suspend operator fun invoke(): Flow<Event> = eventRepository.getEventFlow()
}
