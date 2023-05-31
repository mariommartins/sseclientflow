package com.sseclientflow.di

import com.sseclientflow.data.di.DataModule
import com.sseclientflow.domain.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HiltModuleBinder : DomainModule, DataModule