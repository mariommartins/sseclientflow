package com.sseclientflow.data.datasource

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.sseclientflow.data.api.client.SSERequestClient
import com.sseclientflow.data.api.model.SSEEventState
import com.sseclientflow.data.model.EventResponse
import com.sseclientflow.data.model.EventResponseError
import com.sseclientflow.data.model.EventResponseWrapper
import com.sseclientflow.domain.model.CallResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(
    private val sseRequestClient: SSERequestClient,
    private val gson: Gson
) : EventRemoteDataSource {
    private var eventCount = 0

    override suspend fun getEventFlow(): Flow<CallResult<EventResponse, EventResponseError>> =
        sseRequestClient.connect().map { apiEvent ->
            eventCount++
            when (apiEvent) {
                is SSEEventState.OnEvent ->
                    tryParsingAsEventModel(apiEvent)
                is SSEEventState.OnFailure ->
                    CallResult.Error(EventResponseError.EventFailure(apiEvent.info))
                is SSEEventState.OnConnectionFailure ->
                    CallResult.Error(EventResponseError.ConnectionFailure(apiEvent.info))
                else -> setupEmptyEventResult(apiEvent)
            }
        }

    override fun clearEventFlow() = sseRequestClient.clearConnection()

    private fun tryParsingAsEventModel(
        apiEvent: SSEEventState.OnEvent
    ): CallResult<EventResponse, EventResponseError> {
        return try {
            val resultWrapper = gson.fromJson(apiEvent.data, EventResponseWrapper::class.java)
            val result = resultWrapper?.data ?: return setupEmptyEventResult(apiEvent)
            result.type =
                "eventCount: " + eventCount + " | " + apiEvent.type + " | " + apiEvent.name
            CallResult.Success(result)
        } catch (e: JsonParseException) {
            CallResult.Error(EventResponseError.ParsingError(e.message))
        } catch (e: JsonSyntaxException) {
            CallResult.Error(EventResponseError.ParsingError(e.message))
        }
    }

    private fun setupEmptyEventResult(apiEvent: SSEEventState) =
        CallResult.Success(EventResponse().apply { type = apiEvent.name })
}