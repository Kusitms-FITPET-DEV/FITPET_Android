package com.example.fitpet.ui.insurance.charge.cause

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCauseViewModel @Inject constructor(): BaseViewModel<InsuranceChargeCausePageState>() {

    private val _currentDate: MutableStateFlow<String> = MutableStateFlow("")
    private val _selectedDate: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeCausePageState(
        currentDate = _currentDate.asStateFlow(),
        selectedDate = _selectedDate.asStateFlow()
    )

    fun initSetCurrentDate() {
        val currentDateTime = LocalDateTime.now()

        viewModelScope.launch {
            _currentDate.update { "${currentDateTime.year}. ${currentDateTime.monthValue}. ${currentDateTime.dayOfMonth}" }
        }
    }

    fun onClickCalendar() {
        emitEventFlow(InsuranceChargeCauseEvent.ClickCalendar)
    }

    fun getSelectedDate(date: String) {
        _selectedDate.update { date }
    }
}