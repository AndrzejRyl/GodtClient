package com.fleenmobile.androidinterviewtask.util.injection.module

import com.fleenmobile.androidinterviewtask.api.ApiService
import com.fleenmobile.androidinterviewtask.util.repository.Repository
import com.fleenmobile.androidinterviewtask.util.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun repository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}