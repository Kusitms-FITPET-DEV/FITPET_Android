package com.example.fitpet.ui.insurance.charge.contact

import androidx.fragment.app.viewModels
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeContactBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import com.example.fitpet.ui.insurance.charge.contact.agree.InsuranceAgreeBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsuranceChargeContactFragment: BaseFragment<FragmentInsuranceChargeContactBinding, InsuranceChargeContactPageState, InsuranceChargeContactViewModel>(
    FragmentInsuranceChargeContactBinding::inflate
) {

    override val viewModel: InsuranceChargeContactViewModel by viewModels()

    override fun initView() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        launchWhenStarted(viewLifecycleOwner) {
            launch {
                viewModel.eventFlow.collect { event ->
                    handleEvent(event as InsuranceChargeContactEvent)
                }
            }
        }
    }

    private fun handleEvent(event: InsuranceChargeContactEvent) {
        when (event) {
            InsuranceChargeContactEvent.ClickNextAgreeBtn -> showAgreeBottomSheet()
        }
    }

    private fun showAgreeBottomSheet() {
        InsuranceAgreeBottomSheet().show(parentFragmentManager, BOTTOM_SHEET)
    }
}