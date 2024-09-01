package com.example.fitpet.ui.insurance.charge.cause

import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCauseViewModel @Inject constructor(): BaseViewModel<InsuranceChargeCausePageState>() {

    private val _selectedDate: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeCausePageState(
        selectedDate = _selectedDate.asStateFlow()
    )

    fun onClickCalendar() {
        emitEventFlow(InsuranceChargeCauseEvent.ClickCalendar)
    }

    fun getSelectedDate(date: String) {
        _selectedDate.update { date }
    }
}