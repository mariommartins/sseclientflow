package com.sseclientflow.data.model

internal data class EventResponseWrapper(
    val data: EventResponse? = null
)

internal data class EventResponse(
    val profiles: List<String> = emptyList()
) {
    var type: String? = null
}