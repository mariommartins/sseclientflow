package com.sseclientflow.di

import com.sseclientflow.data.di.DataModule
import com.sseclientflow.domain.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DomainModule::class,
        DataModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface HiltModuleBinder