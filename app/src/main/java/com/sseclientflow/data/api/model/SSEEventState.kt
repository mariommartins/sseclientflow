package com.sseclientflow.data.api.model

sealed class SSEEventState {
    object Empty : SSEEventState()
    object OnCreated : SSEEventState()
    object OnOpen : SSEEventState()
    data class OnEvent(val type: String?, val data: String) : SSEEventState()
    object OnClosed : SSEEventState()
    data class OnFailure(val info: String, val code: Int?) : SSEEventState()
    data class OnConnectionFailure(val info: String) : SSEEventState()

    val name: String get() = this::class.java.name.substringAfter("SSEEventState")
}
