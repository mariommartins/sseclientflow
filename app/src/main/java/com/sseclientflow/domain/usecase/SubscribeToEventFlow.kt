package com.sseclientflow.domain.usecase

import com.sseclientflow.domain.model.Event
import com.sseclientflow.domain.repository.EventRepository
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
