package com.sseclientflow.log

import android.util.Log
import com.sseclientflow.domain.log.SystemLogger
import javax.inject.Inject

class SystemLoggerImpl @Inject constructor() : SystemLogger {
    override fun log(title: String, message: String) {
        Log.d(title, message)
    }

    override fun logError(title: String, message: String) {
        Log.e(title, message)
    }
}