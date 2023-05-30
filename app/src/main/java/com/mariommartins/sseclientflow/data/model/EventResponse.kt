package com.mariommartins.sseclientflow.data.model

data class EventResponseWrapper(
    val data: EventResponse? = null
)

data class EventResponse(
    val profiles: List<String> = emptyList()
) {
    var type: String? = null
}