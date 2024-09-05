package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.insurance.charge.finish.InsuranceChargeCheckEvent
import com.example.fitpet.ui.model.InsuranceCharge
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCheckViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<InsuranceChargeCheckPageState>() {

    override val uiState = InsuranceChargeCheckPageState()

    fun setInsuranceData(contactArgument: InsuranceCharge) {
        if (contactArgument.contactMethodEmail.isNotEmpty()) {
            uiState.contactType = resourceProvider.getString(R.string.insurance_charge_contact_email)
            uiState.contactMethodEmail = contactArgument.contactMethodEmail
        }
    }

    fun onClickFinishBtn() {
        emitEventFlow(InsuranceChargeCheckEvent.GoToFinishPage)
    }
}