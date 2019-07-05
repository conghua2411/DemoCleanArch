package com.example.presentation.base

import androidx.lifecycle.ViewModel
import com.example.presentation.SingleLiveData
import com.example.presentation.event.BaseEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val eventViewModel = SingleLiveData<BaseEvent>()

    fun getCompositeDisposable(): CompositeDisposable = compositeDisposable

    fun getEventViewModel() = eventViewModel

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}