package com.example.fitpet.ui.insurance.charge.cause

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeCauseBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import com.example.fitpet.ui.model.InsuranceCharge
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
        binding.tbInsuranceCharge.btnTopBarInsuranceChargeBack.setOnClickListener { findNavController().popBackStack() }
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
            InsuranceChargeCauseEvent.GoToDocumentPage -> goToDocumentPage()
        }
    }

    private fun showCalendarBottomSheet() {
        Timber.d("[보험금 청구: 1/5] 캘린더 바텀시트")
        CalendarBottomSheet { viewModel.getSelectedDate(it) }.show(
            parentFragmentManager,
            BOTTOM_SHEET
        )
    }

    private fun goToDocumentPage() {
        val action = InsuranceChargeCauseFragmentDirections.actionInsuranceChargeCauseToDocument(setCauseData())
        findNavController().navigate(action)
    }

    private fun setCauseData(): InsuranceCharge {
        val arguments: InsuranceChargeCauseFragmentArgs by navArgs()
        val beforeData = arguments.insuranceArgument

        with (viewModel.uiState) {
            val causeData = InsuranceCharge(petId = beforeData.petId, targetName = beforeData.targetName, causeType = selectedCause.value, hospitalVisitDate = selectedDate.value)
            return causeData
        }
    }

}