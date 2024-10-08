package com.example.fitpet.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.Event
import com.example.fitpet.PageState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE: PageState> : ViewModel() {

    abstract val uiState:STATE

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected fun emitEventFlow(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun showLoading() { _isLoading.value = true }

    private fun endLoading() { _isLoading.value = false }

    protected fun<D> resultResponse(
        response: Result<D>,
        successCallback: (D) -> Unit,
        errorCallback: ((Throwable) -> Unit)? = null,
        needLoading: Boolean = false
    ) {
        if (needLoading) showLoading()

        response.fold(
            onSuccess = { data ->
                successCallback.invoke(data) // 성공했을 때
            },
            onFailure = { throwable ->
                errorCallback?.invoke(throwable) // 실패했을 때
            }
        )

        if (needLoading) endLoading()
    }
}