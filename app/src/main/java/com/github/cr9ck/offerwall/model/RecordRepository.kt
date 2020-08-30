package com.github.cr9ck.offerwall.model

import com.github.cr9ck.offerwall.model.data.RecordDetails
import com.github.cr9ck.offerwall.model.data.RecordModel
import io.reactivex.rxjava3.core.Single

interface RecordRepository {

    fun getRecords(): Single<List<RecordModel>>

    fun getRecordDetails(recordId: Long): Single<RecordDetails>
}