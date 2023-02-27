package com.mariommartins.sseclientflow.data.datasource

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.mariommartins.sseclientflow.data.api.client.SSERequestClient
import com.mariommartins.sseclientflow.data.api.client.SSERequestClientImpl
import com.mariommartins.sseclientflow.data.api.model.SSEEventState
import com.mariommartins.sseclientflow.data.model.EventComplexDataModel
import com.mariommartins.sseclientflow.data.model.EventDataError
import com.mariommartins.sseclientflow.data.model.EventDataModel
import com.mariommartins.sseclientflow.domain.model.CallResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRemoteDataSourceImpl constructor(
    private val sseRequestClient: SSERequestClient = SSERequestClientImpl(),
    private val gson: Gson = Gson()
) : EventRemoteDataSource {

    private var eventCount = 0

    override suspend fun getEventFlow(): Flow<CallResult<EventDataModel, EventDataError>> =
        sseRequestClient.connect().map { apiEvent ->
            eventCount++
            when (apiEvent) {
                is SSEEventState.OnEvent ->
                    tryParsingAsEventModel(apiEvent)
                is SSEEventState.OnFailure ->
                    CallResult.Error(EventDataError.EventFailure(apiEvent.info))
                is SSEEventState.OnConnectionFailure ->
                    CallResult.Error(EventDataError.ConnectionFailure(apiEvent.info))
                else -> {
                    val result = EventDataModel().apply { type = apiEvent.name }
                    CallResult.Success(result)
                }
            }
        }

    override fun clearEventFlow() = sseRequestClient.clearConnection()

    private fun tryParsingAsEventModel(
        apiEvent: SSEEventState.OnEvent
    ): CallResult<EventDataModel, EventDataError> = try {
        val complexResult = gson.fromJson(apiEvent.data, EventComplexDataModel::class.java)
        val result = EventDataModel(complexResult?.data?.profiles?.last())
        result.type = "eventCount: " + eventCount + " | " + apiEvent.type + " | " + apiEvent.name
        CallResult.Success(result)
    } catch (e: JsonParseException) {
        CallResult.Error(EventDataError.ParsingError(e.message))
    } catch (e: JsonSyntaxException) {
        CallResult.Error(EventDataError.ParsingError(e.message))
    }
}