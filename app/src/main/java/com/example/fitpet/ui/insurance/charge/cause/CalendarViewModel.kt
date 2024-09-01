package com.example.fitpet.ui.insurance.charge.cause

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.CalendarDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : BaseViewModel<CalendarPageState>() {

    private val _currentYear: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _currentMonth: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _monthDateList: MutableStateFlow<List<CalendarDate>> = MutableStateFlow(emptyList())

    override val uiState = CalendarPageState(
        currentYear = _currentYear.asStateFlow(),
        currentMonth = _currentMonth.asStateFlow(),
        monthDateList = _monthDateList.asStateFlow()
    )

    fun initSetCalendarValue() {
        viewModelScope.launch {
            _currentYear.update { LocalDateTime.now().year }
            _currentMonth.update { LocalDateTime.now().monthValue }
            _monthDateList.update { setMonthDate() }
        }
    }

    private fun setMonthDate(): List<CalendarDate> {
        val currentMonthDateList = ArrayList<CalendarDate>()

        //

        return currentMonthDateList
    }
}