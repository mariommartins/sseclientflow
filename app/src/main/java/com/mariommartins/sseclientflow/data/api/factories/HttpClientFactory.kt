package com.mariommartins.sseclientflow.data.api.factories

import com.mariommartins.sseclientflow.data.api.configuration.ApiConfiguration
import okhttp3.OkHttpClient

internal class HttpClientFactory constructor(private val apiConfiguration: ApiConfiguration = ApiConfiguration()) {
    private val okHttpClient by lazy { OkHttpClient() }

    fun create(): OkHttpClient.Builder {
        return with(apiConfiguration) {
            okHttpClient
                .newBuilder()
                .connectTimeout(timeout.connect, timeout.defaultTimeUnit)
                .callTimeout(timeout.call, timeout.defaultTimeUnit)
                .readTimeout(timeout.read, timeout.defaultTimeUnit)
                .writeTimeout(timeout.write, timeout.defaultTimeUnit)
                .retryOnConnectionFailure(retry.connectionFailed)
        }
    }
}
