package com.mariommartins.sseclientflow.domain.di

import com.mariommartins.sseclientflow.domain.usecase.SubscribeToEventFlow
import com.mariommartins.sseclientflow.domain.usecase.SubscribeToEventFlowImpl
import com.mariommartins.sseclientflow.domain.usecase.UnsubscribeFromEventFlow
import com.mariommartins.sseclientflow.domain.usecase.UnsubscribeFromEventFlowImpl
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