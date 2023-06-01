package com.sseclientflow.di

import com.sseclientflow.BuildConfig
import com.sseclientflow.data.api.client.SSE_BASE_URL
import com.sseclientflow.domain.log.SystemLogger
import com.sseclientflow.log.SystemLoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class AndroidSupportModule {
    @Binds
    @Reusable
    abstract fun bindsSystemLogger(systemLogger: SystemLoggerImpl): SystemLogger

    companion object {
        @Provides
        @Named(SSE_BASE_URL)
        fun providesBaseUrl(): String = BuildConfig.SSE_BASE_URL
    }
}