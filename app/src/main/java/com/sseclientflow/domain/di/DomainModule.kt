package com.sseclientflow.domain.di

import com.sseclientflow.domain.usecase.SubscribeToEventFlow
import com.sseclientflow.domain.usecase.SubscribeToEventFlowImpl
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlow
import com.sseclientflow.domain.usecase.UnsubscribeFromEventFlowImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindSubscribeToEventFlow(
        subscribeToEventFlow: SubscribeToEventFlowImpl
    ): SubscribeToEventFlow

    @Binds
    abstract fun bindUnsubscribeFromEventFlow(
        unsubscribeFromEventFlow: UnsubscribeFromEventFlowImpl
    ): UnsubscribeFromEventFlow
}