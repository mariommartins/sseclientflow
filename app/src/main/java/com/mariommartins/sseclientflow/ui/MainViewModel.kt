package com.mariommartins.sseclientflow.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariommartins.sseclientflow.domain.usecase.SubscribeToEventFlow
import com.mariommartins.sseclientflow.domain.usecase.UnsubscribeFromEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val subscribeToEventFlow: SubscribeToEventFlow,
    private val unsubscribeFromEventFlow: UnsubscribeFromEventFlow
) : ViewModel() {
    private val _eventContentLiveData = MutableLiveData<String>()
    val eventContentLiveData: LiveData<String> get() = _eventContentLiveData

    private val _eventTypeLiveData = MutableLiveData<String>()
    val eventTypeLiveData: LiveData<String> get() = _eventTypeLiveData

    init {
        viewModelScope.launch {
            subscribeToEventFlow()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    Log.e("SSE-ERROR", e.toString())
                    _eventTypeLiveData.postValue(e::class.java.name)
                }
                .collect { event ->
                    _eventTypeLiveData.postValue(event.type)
                    _eventContentLiveData.postValue(event.content)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        unsubscribeFromEventFlow()
    }
}
