package com.example.fitpet.ui.insurance.charge.cause

import com.example.fitpet.PageState
import com.example.fitpet.model.CalendarDate
import kotlinx.coroutines.flow.StateFlow

data class CalendarPageState(
    val currentYear: StateFlow<Int>,
    val currentMonth: StateFlow<Int>,
    val monthDateList: StateFlow<List<CalendarDate>>,
    val selectedDate: StateFlow<Int>
): PageState