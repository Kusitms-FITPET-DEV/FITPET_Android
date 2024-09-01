package com.example.fitpet.ui.insurance.charge.cause.calendar

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.CalendarDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : BaseViewModel<CalendarPageState>() {

    private val _currentYear: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _currentMonth: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _monthDateList: MutableStateFlow<List<CalendarDate>> = MutableStateFlow(emptyList())
    private val _selectedDate: MutableStateFlow<Int> = MutableStateFlow(0)

    private val currentDateTime = LocalDateTime.now()

    override val uiState = CalendarPageState(
        currentYear = _currentYear.asStateFlow(),
        currentMonth = _currentMonth.asStateFlow(),
        monthDateList = _monthDateList.asStateFlow(),
        selectedDate = _selectedDate.asStateFlow()
    )

    fun initSetCalendarValue() {
        viewModelScope.launch {
            _currentYear.update { currentDateTime.year }
            _currentMonth.update { currentDateTime.monthValue }
            _monthDateList.update { setMonthDate() }
        }
    }

    fun setSelectedDate(date: Int) {
        _selectedDate.update { date }
    }

    private fun setMonthDate(): List<CalendarDate> {
        val currentMonthDateList = ArrayList<CalendarDate>()

        val lastDayOfMonth = YearMonth.of(currentDateTime.year, currentDateTime.monthValue).lengthOfMonth()
        for (i in 1 until lastDayOfMonth + 1) {
            currentMonthDateList.add(CalendarDate(i))
        }

        return currentMonthDateList
    }
}