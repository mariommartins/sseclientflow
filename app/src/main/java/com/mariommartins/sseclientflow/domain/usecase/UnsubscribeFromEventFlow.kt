package com.mariommartins.sseclientflow.domain.usecase

import com.mariommartins.sseclientflow.data.repository.EventRepositoryImpl
import com.mariommartins.sseclientflow.domain.repository.EventRepository

class UnsubscribeFromEventFlow constructor(
    private val eventRepository: EventRepository = EventRepositoryImpl(),
) {
    fun execute() = eventRepository.clearEventFlow()
}
