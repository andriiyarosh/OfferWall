package com.github.cr9ck.offerwall.model.network

import com.github.cr9ck.offerwall.model.data.RecordDetails
import com.github.cr9ck.offerwall.model.data.RecordModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v1/trending")
    fun getRecords(): Single<List<RecordModel>>

    @GET("api/v1/object/{id}")
    fun getRecord(@Path("id") id: Long): Single<RecordDetails>
}