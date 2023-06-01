package com.sseclientflow.domain.log

interface SystemLogger {
    fun log(title: String, message: String)
    fun logError(title: String, message: String)
}