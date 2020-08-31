package com.github.cr9ck.offerwall.model

import com.github.cr9ck.offerwall.model.network.ApiService

class RecordRepositoryImpl constructor(private val apiService: ApiService): RecordRepository {

    override fun getRecords() = apiService.getRecords()

    override fun getRecordDetails(recordId: Long) = apiService.getRecord(recordId)
}