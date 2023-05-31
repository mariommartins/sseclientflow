package com.sseclientflow.data.mapper

import com.sseclientflow.data.model.EventResponse
import com.sseclientflow.domain.model.Event
import javax.inject.Inject

class EventMapper @Inject constructor() {
    fun map(from: EventResponse?): Event = Event(
        type = from?.type,
        content = from?.profiles?.lastOrNull()
    )
}
