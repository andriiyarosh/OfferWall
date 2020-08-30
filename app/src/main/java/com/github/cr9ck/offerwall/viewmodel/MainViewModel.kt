package com.github.cr9ck.offerwall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.cr9ck.offerwall.model.RecordRepository
import com.github.cr9ck.offerwall.model.data.RecordDetails
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val recordRepository: RecordRepository) :
    ViewModel() {

    val viewState: LiveData<ViewState>
        get() = MutableLiveData<ViewState>().getRecords()

    private fun MutableLiveData<ViewState>.getRecords(): MutableLiveData<ViewState> {
        recordRepository.getRecords()
            .subscribeOn(Schedulers.io())
            .flatMap {
                recordRepository.getRecordDetails(it.first().id)
            }
            .blockingSubscribe(
                {
                    getStateByType(it)
                },
                {
                    it.printStackTrace()
                    value = ViewState.Error
                })
        return this
    }

    private fun getStateByType(recordDetails: RecordDetails): ViewState {
        return ViewState.TextState("recordDetails")
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

        object Error : ViewState()
    }
}