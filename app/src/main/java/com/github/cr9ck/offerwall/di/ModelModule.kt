package com.github.cr9ck.offerwall.di

import com.github.cr9ck.offerwall.model.network.ApiService
import com.github.cr9ck.offerwall.model.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelModule {

    @Singleton
    @Provides
    fun provideApiService() = ApiServiceFactory.createService(
        ApiService::class.java)
}