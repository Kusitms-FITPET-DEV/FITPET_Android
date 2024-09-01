package com.example.fitpet.ui.insurance.charge

import androidx.fragment.app.viewModels
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCauseBinding
import com.example.fitpet.ui.insurance.charge.cause.CalendarBottomSheet
import com.example.fitpet.ui.insurance.charge.cause.CalendarBottomSheet.Companion.BOTTOM_SHEET
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceChargeCauseFragment :
    BaseFragment<FragmentInsuranceChargeCauseBinding, PageState.Default, InsuranceChargeCauseViewModel>(
        FragmentInsuranceChargeCauseBinding::inflate
    ) {

    override val viewModel: InsuranceChargeCauseViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeCauseEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeCauseEvent) {
        when (event) {
            InsuranceChargeCauseEvent.ClickCalendar -> showCalendarBottomSheet()
        }
    }

    private fun showCalendarBottomSheet() {
        Timber.d("[보험금 청구: 1/5] 캘린더 바텀시트")
        CalendarBottomSheet().show(parentFragmentManager, BOTTOM_SHEET)
    }

}