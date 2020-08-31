package com.github.cr9ck.offerwall.di

import com.github.cr9ck.offerwall.model.RecordRepository
import com.github.cr9ck.offerwall.model.RecordRepositoryImpl
import com.github.cr9ck.offerwall.model.data.RecordDetails
import com.github.cr9ck.offerwall.model.data.RecordModel
import com.github.cr9ck.offerwall.model.network.ApiService
import com.github.cr9ck.offerwall.model.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.*
import javax.inject.Singleton

@Module
class ModelModule {

    @Singleton
    @Provides
    fun provideApiService() = ApiServiceFactory.createService(ApiService::class.java)

    @Provides
    fun provideRecordRepository(apiService: ApiService): RecordRepository = RecordRepositoryImpl(apiService)

    @Provides
    fun provideRecordQueue(): Queue<RecordModel> = ArrayDeque<RecordModel>()

    @Provides
    fun provideSubject(): Subject<RecordDetails> = BehaviorSubject.create<RecordDetails>()
}