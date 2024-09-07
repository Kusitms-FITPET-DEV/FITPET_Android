package com.example.fitpet.ui.insurance.charge.cause.calendar

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.CalendarDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDateTime
import org.threeten.bp.YearMonth
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : BaseViewModel<CalendarPageState>() {

    private val _currentYear: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _currentMonth: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _currentCalendarPage: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _monthDateList: MutableStateFlow<List<CalendarDate>> = MutableStateFlow(emptyList())
    private val _selectedDate: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _selectedDatePositionInAdapter: MutableStateFlow<Int> = MutableStateFlow(0)

    private val currentDateTime = LocalDateTime.now()

    override val uiState = CalendarPageState(
        currentYear = _currentYear.asStateFlow(),
        currentMonth = _currentMonth.asStateFlow(),
        currentCalendarPage = _currentCalendarPage.asStateFlow(),
        monthDateList = _monthDateList.asStateFlow(),
        selectedDate = _selectedDate.asStateFlow(),
        selectedDatePositionInAdapter = _selectedDatePositionInAdapter.asStateFlow()
    )

    fun initSetCalendarValue() {
        viewModelScope.launch {
            _currentYear.update { currentDateTime.year }
            _currentMonth.update { currentDateTime.monthValue }
            _monthDateList.update { setMonthDateList(currentDateTime.monthValue) }
            _selectedDatePositionInAdapter.update { RecyclerView.NO_POSITION }
        }
    }

    fun setCurrentPage(movePage: Int) {
        viewModelScope.launch {
            _currentCalendarPage.update { it + movePage }
            _currentMonth.update { currentDateTime.monthValue + _currentCalendarPage.value }
            _monthDateList.update { setMonthDateList(_currentMonth.value) }
        }
    }

    fun setSelectedDate(date: Int) {
        _selectedDate.update { date }
    }

    private fun setMonthDateList(currentPageMonth: Int): List<CalendarDate> {
        val currentMonthDateList = ArrayList<CalendarDate>()

        val firstDayOfWeek = getFirstDayOfWeek(currentPageMonth)
        val lastDayOfMonth = YearMonth.of(currentDateTime.year, currentPageMonth).lengthOfMonth()

        for (i in 0 until setFirstDayStartDate(firstDayOfWeek.toString())) {
            currentMonthDateList.add(CalendarDate(0))
        }

        for (i in 1 until lastDayOfMonth + 1) {
            currentMonthDateList.add(CalendarDate(i))
        }

        return currentMonthDateList
    }

    private fun getFirstDayOfWeek(currentPageMonth: Int): DayOfWeek {
        val firstDayOfMonth = LocalDateTime.of(currentDateTime.year, currentPageMonth, FIRST_DAY_OF_MONTH, FIRST_DAY_HOUR, FIRST_DAY_MINUTE)
        return firstDayOfMonth.dayOfWeek
    }

    private fun setFirstDayStartDate(firstDayOfWeek: String): Int {
        val startDate = when (firstDayOfWeek) {
            MONDAY -> MONDAY_NUM
            TUESDAY -> TUESDAY_NUM
            WEDNESDAY -> WEDNESDAY_NUM
            THURSDAY -> THURSDAY_NUM
            FRIDAY -> FRIDAY_NUM
            SATURDAY -> SATURDAY_NUM
            else -> SUNDAY_NUM
        }

        return startDate
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }

    companion object {
        const val FIRST_DAY_OF_MONTH = 1
        const val FIRST_DAY_HOUR = 0
        const val FIRST_DAY_MINUTE = 0
        const val MONDAY = "MONDAY"
        const val TUESDAY = "TUESDAY"
        const val WEDNESDAY = "WEDNESDAY"
        const val THURSDAY = "THURSDAY"
        const val FRIDAY = "FRIDAY"
        const val SATURDAY = "SATURDAY"
        const val SUNDAY = "SUNDAY"
        const val MONDAY_NUM = 1
        const val TUESDAY_NUM = 2
        const val WEDNESDAY_NUM = 3
        const val THURSDAY_NUM = 4
        const val FRIDAY_NUM = 5
        const val SATURDAY_NUM = 6
        const val SUNDAY_NUM = 0
    }
}