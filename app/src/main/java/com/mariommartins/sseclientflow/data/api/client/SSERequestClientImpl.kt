package com.mariommartins.sseclientflow.data.api.client

import android.util.Log
import com.mariommartins.sseclientflow.data.api.factories.HttpClientFactory
import com.mariommartins.sseclientflow.data.api.model.SSEEventState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.*
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.io.IOException

private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/updates.json"
private const val HEADER_NAME = "Accept"
private const val HEADER_EVENT_STREAM_VALUE = "text/event-stream"

private const val LOG_TAG = "SSE"
private const val LOG_OPEN_CONNECTION = "Connection Opened"
private const val LOG_CLOSED_CONNECTION = "Connection Closed"
private const val LOG_EVENT_RECEIVED = "On Event Received! Data -: "
private const val LOG_FAILURE = "On Failure -: "

internal class SSERequestClientImpl(
    private val okHttpClient: OkHttpClient = HttpClientFactory().create().build(),
    private val baseUrl: String = BASE_URL
) : SSERequestClient {
    private lateinit var eventFlow: MutableStateFlow<SSEEventState>
    private lateinit var request: Request
    private var eventSource: EventSource? = null
    private val eventSourceListener
        get() = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                super.onOpen(eventSource, response)
                Log.d(LOG_TAG, LOG_OPEN_CONNECTION)
                eventFlow.value = SSEEventState.OnOpen
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                Log.d(LOG_TAG, LOG_CLOSED_CONNECTION)
                eventFlow.value = SSEEventState.OnClosed
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                Log.d(LOG_TAG, LOG_EVENT_RECEIVED + data)
                eventFlow.value = SSEEventState.OnEvent(type, data)
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                (LOG_FAILURE + t?.message + response).let { failureInfo ->
                    Log.d(LOG_TAG, failureInfo)
                    eventFlow.value = SSEEventState.OnFailure(failureInfo, response?.code)
                }
            }
        }

    override suspend fun connect(): Flow<SSEEventState> {
        eventFlow = MutableStateFlow(SSEEventState.Empty)
        request = createRequest()
        eventSource = createEventSource(request)
        okHttpClient.newCall(request).enqueue(
            responseCallback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    eventFlow.value = SSEEventState.OnConnectionFailure(e.message ?: "")
                }

                override fun onResponse(call: Call, response: Response) {
                    eventFlow.value = SSEEventState.OnCreated
                }
            }
        )
        return eventFlow
    }

    override fun clearConnection() {
        eventSource?.cancel()
    }

    private fun createRequest() =
        Request.Builder()
            .url(baseUrl)
            .addHeader(HEADER_NAME, HEADER_EVENT_STREAM_VALUE)
            .get()
            .build()

    private fun createEventSource(request: Request) =
        EventSources.createFactory(okHttpClient)
            .newEventSource(request = request, listener = eventSourceListener)
}
