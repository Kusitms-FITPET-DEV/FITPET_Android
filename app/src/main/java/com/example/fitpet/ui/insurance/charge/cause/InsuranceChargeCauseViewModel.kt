package com.example.fitpet.ui.insurance.charge.cause

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCauseViewModel @Inject constructor() :
    BaseViewModel<InsuranceChargeCausePageState>() {

    private val _selectedCause: MutableStateFlow<String> = MutableStateFlow("")
    private val _currentDate: MutableStateFlow<String> = MutableStateFlow("")
    private val _selectedDate: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeCausePageState(
        selectedCause = _selectedCause.asStateFlow(),
        currentDate = _currentDate.asStateFlow(),
        selectedDate = _selectedDate.asStateFlow(),
        isBtnEnabled = combine(_selectedCause, _selectedDate) { cause, date ->
            isGoNextValid(cause, date)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun initSetCurrentDate() {
        val currentDateTime = LocalDateTime.now()

        viewModelScope.launch {
            _currentDate.update { "${currentDateTime.year}. ${currentDateTime.monthValue}. ${currentDateTime.dayOfMonth}" }
        }
    }

    fun onClickChargeCause(cause: String) {
        viewModelScope.launch {
            _selectedCause.update { cause }
        }
    }

    fun onClickCalendar() {
        emitEventFlow(InsuranceChargeCauseEvent.ClickCalendar)
    }

    fun getSelectedDate(date: String) {
        viewModelScope.launch {
            _selectedDate.update { date }
        }
    }

    fun onClickNextBtn() {
        emitEventFlow(InsuranceChargeCauseEvent.GoToDocumentPage)
    }

    private fun isGoNextValid(cause: String, date: String): Boolean {
        return cause.isNotEmpty() && date.isNotEmpty()
    }
}