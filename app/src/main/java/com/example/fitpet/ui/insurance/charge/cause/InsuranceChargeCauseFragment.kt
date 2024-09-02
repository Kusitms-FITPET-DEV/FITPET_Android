package com.example.fitpet.ui.insurance.charge.cause

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCauseBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class InsuranceChargeCauseFragment :
    BaseFragment<FragmentInsuranceChargeCauseBinding, InsuranceChargeCausePageState, InsuranceChargeCauseViewModel>(
        FragmentInsuranceChargeCauseBinding::inflate
    ) {

    override val viewModel: InsuranceChargeCauseViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.initSetCurrentDate()
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeCauseEvent)
                }
            }
            launch {

                viewModel.uiState.selectedCause.collect { cause ->
                    with (binding) {
                        when (cause) {
                            getString(R.string.insurance_charge_cause_injury) -> tvInsuranceChargeCauseInjury.setTextAppearance(R.style.b4_S)
                            getString(R.string.insurance_charge_cause_hospital) -> tvInsuranceChargeCauseHospital.setTextAppearance(R.style.b4_S)
                            getString(R.string.insurance_charge_cause_etc) -> tvInsuranceChargeCauseEtc.setTextAppearance(R.style.b4_S)
                        }
                    }
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeCauseEvent) {
        when (event) {
            InsuranceChargeCauseEvent.ClickCalendar -> showCalendarBottomSheet()
            InsuranceChargeCauseEvent.GoToDocumentPage -> goToDocumentPage()
        }
    }

    private fun showCalendarBottomSheet() {
        Timber.d("[보험금 청구: 1/5] 캘린더 바텀시트")
        CalendarBottomSheet{viewModel.getSelectedDate(it)}.show(parentFragmentManager, BOTTOM_SHEET)
    }

    private fun goToDocumentPage() {
        val action = InsuranceChargeCauseFragmentDirections.actionInsuranceChargeCauseToDocument()
        findNavController().navigate(action, NavOptions.Builder().build())
    }

}