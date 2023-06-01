package com.sseclientflow.domain.usecase

import com.sseclientflow.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface SubscribeToEventFlow {
    suspend operator fun invoke(): Flow<Event>
}