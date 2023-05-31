package com.sseclientflow.domain.usecase

import com.sseclientflow.domain.model.Event
import com.sseclientflow.domain.repository.EventRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SubscribeToEventFlowImpl @Inject constructor(
    private val eventRepository: EventRepository
) : SubscribeToEventFlow {
    override suspend operator fun invoke(): Flow<Event> = eventRepository.getEventFlow()
}
