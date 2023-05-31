package com.sseclientflow.domain.usecase

import com.sseclientflow.domain.repository.EventRepository
import javax.inject.Inject

interface UnsubscribeFromEventFlow {
    operator fun invoke()
}

class UnsubscribeFromEventFlowImpl @Inject constructor(
    private val eventRepository: EventRepository
) : UnsubscribeFromEventFlow {
    override operator fun invoke() = eventRepository.clearEventFlow()
}
