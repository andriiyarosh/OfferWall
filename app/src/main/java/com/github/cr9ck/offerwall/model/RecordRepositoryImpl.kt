package com.github.cr9ck.offerwall.model

import com.github.cr9ck.offerwall.model.network.ApiService
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(private val apiService: ApiService): RecordRepository {

    override fun getRecords() = apiService.getRecords()

    override fun getRecordDetails(recordId: Long) = apiService.getRecord(recordId)
}