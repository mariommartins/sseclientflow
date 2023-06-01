package com.sseclientflow.data.api.client

import com.sseclientflow.data.api.model.SSEEventState
import com.sseclientflow.domain.log.SystemLogger
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import okio.IOException

const val SSE_BASE_URL = "SSE_TEST_API_BASE_URL_NAME"
private const val HEADER_NAME = "Accept"
private const val HEADER_EVENT_STREAM_VALUE = "text/event-stream"
private const val LOG_TAG = "SSE"
private const val LOG_OPEN_CONNECTION = "Connection Opened"
private const val LOG_CLOSED_CONNECTION = "Connection Closed"
private const val LOG_EVENT_RECEIVED = "Event Received | Data -: "
private const val LOG_FAILURE = "On Failure -: "
private const val EMPTY_DATA_BODY = "null"

internal class SSERequestClientImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    @Named(SSE_BASE_URL) private val baseUrl: String,
    private val systemLogger: SystemLogger
) : SSERequestClient {
    private lateinit var eventFlow: MutableStateFlow<SSEEventState>
    private lateinit var request: Request
    private var eventSource: EventSource? = null
    private val eventSourceListener
        get() = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                super.onOpen(eventSource, response)
                systemLogger.log(LOG_TAG, LOG_OPEN_CONNECTION)
                eventFlow.value = SSEEventState.OnOpen
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                systemLogger.log(LOG_TAG, LOG_CLOSED_CONNECTION)
                eventFlow.value = SSEEventState.OnClosed
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                systemLogger.log(LOG_TAG, LOG_EVENT_RECEIVED + data)
                if (data != EMPTY_DATA_BODY) eventFlow.value = SSEEventState.OnEvent(type, data)
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                (LOG_FAILURE + t?.message + response).let { failureInfo ->
                    systemLogger.log(LOG_TAG, failureInfo)
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
