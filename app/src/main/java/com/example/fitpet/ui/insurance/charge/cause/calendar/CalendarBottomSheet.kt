package com.example.fitpet.ui.insurance.charge.cause.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fitpet.databinding.BottomSheetCalendarBinding
import com.example.fitpet.model.CalendarDate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import timber.log.Timber

class CalendarBottomSheet(
    private val onSelectedDate: (String) -> Unit
): BottomSheetDialogFragment() {

    private val viewModel by viewModels<CalendarViewModel>()

    private var _binding: BottomSheetCalendarBinding? = null
    val binding: BottomSheetCalendarBinding
        get() = requireNotNull(_binding as BottomSheetCalendarBinding)

    private var _calendarAdapter: CalendarAdapter? = null
    private val calendarAdapter
        get() = requireNotNull(_calendarAdapter)

    override fun onStart() {
        super.onStart()

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetCalendarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.initSetCalendarValue()

        initSetAdapter()
        initSetDateList()
        clickSelectBtn()
    }

    private fun initSetAdapter() {
        _calendarAdapter = CalendarAdapter(viewModel.uiState.selectedDatePositionInAdapter.value).apply {
            setOnClickListener(object : CalendarAdapter.OnItemClickListener {
                override fun onItemClick(item: CalendarDate, position: Int) {
                    Timber.d("[보험금 청구] 캘린더 클릭 날짜 -> ${item.date}")
                    viewModel.setSelectedDate(item.date)
                }
            })
        }
        binding.rcvCalendarDate.adapter = calendarAdapter
    }

    private fun initSetDateList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.monthDateList.collect {
                        initSetAdapter()
                        viewModel.setSelectedDate(0)
                        calendarAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun clickSelectBtn() {
        binding.btnCalendarDateSelect.setOnClickListener {
            with (viewModel.uiState) {
                onSelectedDate(viewModel.formatDate("${currentYear.value}-${currentMonth.value}-${selectedDate.value}"))
            }
            dismiss()
        }
    }

    companion object {
        const val BOTTOM_SHEET = "BOTTOM_SHEET"
    }
}