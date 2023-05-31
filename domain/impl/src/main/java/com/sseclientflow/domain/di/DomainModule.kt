package com.sseclientflow.domain.di

import com.sseclientflow.domain.usecase.SubscribeToEventFlow
import com.sseclientflow.domain.usecase.SubscribeToEventFlowImpl
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlow
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlowImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindSubscribeToEventFlow(
        subscribeToEventFlow: SubscribeToEventFlowImpl
    ): SubscribeToEventFlow

    @Binds
    fun bindUnsubscribeFromEventFlow(
        unsubscribeFromEventFlow: UnsubscribeFromEventFlowImpl
    ): UnsubscribeFromEventFlow
}