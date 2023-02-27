package com.mariommartins.sseclientflow.data.mapper

import com.mariommartins.sseclientflow.data.model.EventDataModel
import com.mariommartins.sseclientflow.domain.model.EventModel

class EventMapper {
    fun map(from: EventDataModel?): EventModel = EventModel(
        type = from?.type,
        content = from?.content
    )
}
