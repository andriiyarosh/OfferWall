package com.github.cr9ck.offerwall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.cr9ck.offerwall.model.RecordRepository
import com.github.cr9ck.offerwall.model.data.RecordDetails
import com.github.cr9ck.offerwall.model.data.RecordModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.Subject
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val recordRepository: RecordRepository,
    private val recordsQueue: Queue<RecordModel>,
    private val subject: Subject<RecordDetails>
) :
    ViewModel() {

    private val viewStateLD: MutableLiveData<DataLoadingState> = MutableLiveData()
    val viewState: LiveData<DataLoadingState> = viewStateLD

    init {
        addEventConsumer()
        getRecords()
    }

    fun nextRecord() {
        viewStateLD.postValue(DataLoadingState.DataLoading)

        val record = recordsQueue.poll()
        val result = record?.let {
            recordsQueue.add(record)
            recordRepository.getRecordDetails(record.id)
        } ?: Single.just(null)
        result.subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { subject.onNext(it) }
            }) {
                subject.onError(it)
            }

    }

    private fun addEventConsumer() {
        subject
            .subscribe({
                getStateByType(it)?.let { state ->
                    viewStateLD.postValue(DataLoadingState.DataLoaded(state))
                }
            }) {
                it.printStackTrace()
                viewStateLD.postValue(DataLoadingState.Error)
            }
    }

    private fun getRecords() {
        recordRepository.getRecords()
            .subscribeOn(Schedulers.io())
            .subscribe({
                recordsQueue.addAll(it)
                nextRecord()
            }) {
                it.printStackTrace()
            }
    }

    private fun getStateByType(recordDetails: RecordDetails) = when (recordDetails.type) {
            "game" -> ViewState.Game
            "text" -> recordDetails.contents?.let { ViewState.TextState(it) }
            "webview" -> recordDetails.url?.let { ViewState.WebState(it) }
            else -> null
    }

    sealed class DataLoadingState {
        class DataLoaded(val viewState: ViewState) : DataLoadingState()
        object DataLoading : DataLoadingState()
        object Error : DataLoadingState()
    }

    sealed class ViewState {
        class TextState(val text: String) : ViewState() {
            override fun toString(): String {
                return "text"
            }
        }

        class WebState(val url: String) : ViewState() {
            override fun toString(): String {
                return "webview"
            }
        }

        object Game : ViewState() {
            override fun toString(): String {
                return "game"
            }
        }
    }
}