package com.example.fitpet.ui.insurance.charge.contact

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitpet.R
import com.example.fitpet.base.BaseFragment
import com.example.fitpet.databinding.FragmentInsuranceChargeContactBinding
import com.example.fitpet.ui.insurance.charge.cause.calendar.CalendarBottomSheet.Companion.BOTTOM_SHEET
import com.example.fitpet.ui.insurance.charge.contact.agree.InsuranceAgreeBottomSheet
import com.example.fitpet.ui.model.InsuranceCharge
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
            launch {
                viewModel.uiState.isClickedAgreement.collect() { agree ->
                    if (agree) goToNextCheckPage()
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
        InsuranceAgreeBottomSheet{viewModel.onClickAgreeBtn()}.show(parentFragmentManager, BOTTOM_SHEET)
    }

    private fun goToNextCheckPage() {
        val action = InsuranceChargeContactFragmentDirections.actionInsuranceChargeContactToCheck(setChargeData())
        findNavController().navigate(action)
    }

    private fun setChargeData(): InsuranceCharge {
        val blank = getString(R.string.blank)

        with(viewModel.uiState) {
            val methodMsg = if (isSelectedKaKao.value) getString(R.string.insurance_charge_contact_kakao) else blank   // TODO 유저 카카오 로그인 완료 후 카카오계정받기
            val methodEmail = if (isSelectedEmail.value) emailInput.value else blank
            val chargeData = InsuranceCharge(contactMethodMsg = methodMsg, contactMethodEmail = methodEmail)
            return chargeData
        }
    }
}