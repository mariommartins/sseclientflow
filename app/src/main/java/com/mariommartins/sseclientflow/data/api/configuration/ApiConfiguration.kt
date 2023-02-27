package com.mariommartins.sseclientflow.data.api.configuration

import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT = 3L

data class ApiConfiguration(
    val retry: Retry = Retry(),
    val timeout: Timeout = Timeout(),
) {
    data class Retry(
        val connectionFailed: Boolean = true
    )

    data class Timeout(
        val connect: Long = DEFAULT_TIMEOUT,
        val call: Long = DEFAULT_TIMEOUT,
        val read: Long = DEFAULT_TIMEOUT,
        val write: Long = DEFAULT_TIMEOUT,
        val defaultTimeUnit: TimeUnit = TimeUnit.HOURS
    )
}
