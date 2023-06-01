package com.sseclientflow.domain.di

import com.sseclientflow.domain.usecase.SubscribeToEventFlow
import com.sseclientflow.domain.usecase.SubscribeToEventFlowImpl
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlow
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlowImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {
    @Binds
    internal abstract fun bindSubscribeToEventFlow(
        subscribeToEventFlow: SubscribeToEventFlowImpl
    ): SubscribeToEventFlow

    @Binds
    internal abstract fun bindUnsubscribeFromEventFlow(
        unsubscribeFromEventFlow: UnsubscribeFromEventFlowImpl
    ): UnsubscribeFromEventFlow
}