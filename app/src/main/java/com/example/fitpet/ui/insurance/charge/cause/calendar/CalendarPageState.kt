package com.example.fitpet.ui.insurance.charge.cause.calendar

import com.example.fitpet.PageState
import com.example.fitpet.model.CalendarDate
import kotlinx.coroutines.flow.StateFlow

data class CalendarPageState(
    val currentYear: StateFlow<Int>,
    val currentMonth: StateFlow<Int>,
    val currentCalendarPage: StateFlow<Int>,
    val monthDateList: StateFlow<List<CalendarDate>>,
    val selectedDate: StateFlow<Int>,
    val selectedDatePositionInAdapter: StateFlow<Int>
): PageState