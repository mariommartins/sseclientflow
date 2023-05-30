package com.mariommartins.sseclientflow.data.mapper

import com.mariommartins.sseclientflow.data.model.EventResponse
import com.mariommartins.sseclientflow.domain.model.Event

class EventMapper {
    fun map(from: EventResponse?): Event = Event(
        type = from?.type,
        content = from?.profiles?.lastOrNull()
    )
}
