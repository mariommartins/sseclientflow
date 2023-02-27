package com.mariommartins.sseclientflow.data.model

data class EventComplexDataModel (
    val data: EventComplexContentDataModel? = null
)

data class EventComplexContentDataModel (
    val profiles: List<String> = emptyList()
)