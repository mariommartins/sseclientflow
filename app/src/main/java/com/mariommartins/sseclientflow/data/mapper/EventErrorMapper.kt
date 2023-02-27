package com.mariommartins.sseclientflow.data.mapper

import com.mariommartins.sseclientflow.data.model.EventDataError
import com.mariommartins.sseclientflow.domain.model.EventError

class EventErrorMapper {
    fun map(from: EventDataError): EventError = when (from) {
        is EventDataError.ParsingError -> EventError.ParsingError
        is EventDataError.EventFailure -> EventError.EventFailure(from.info)
        is EventDataError.ConnectionFailure -> EventError.ConnectionFailure(from.info)
    }
}
