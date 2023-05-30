package com.mariommartins.sseclientflow.data.mapper

import com.mariommartins.sseclientflow.data.model.EventResponseError
import com.mariommartins.sseclientflow.domain.model.EventError
import javax.inject.Inject

class EventErrorMapper @Inject constructor() {
    fun map(from: EventResponseError): EventError = when (from) {
        is EventResponseError.ParsingError -> EventError.ParsingError
        is EventResponseError.EventFailure -> EventError.EventFailure(from.info)
        is EventResponseError.ConnectionFailure -> EventError.ConnectionFailure(from.info)
    }
}
