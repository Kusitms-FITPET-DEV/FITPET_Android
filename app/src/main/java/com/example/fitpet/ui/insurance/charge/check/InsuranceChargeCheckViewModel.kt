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

    fun setInsuranceData(insuranceArgument: InsuranceCharge) {
        with (uiState) {
            // TODO targetName, phone은 추후 데이터 넘겨받은 후 구현
//            targetName =
            causeType = insuranceArgument.causeType
            hospitalVisitDate = insuranceArgument.hospitalVisitDate
            receiptUrl = insuranceArgument.receiptUrl
            medicalExpensesUrl = insuranceArgument.medicalExpensesUrl
            etcUrl = insuranceArgument.etcUrl
            accountBank = insuranceArgument.accountBank
            accountNumber = insuranceArgument.accountNumber
            contactMethodMsg = insuranceArgument.contactMethodMsg
            contactMethodEmail = insuranceArgument.contactMethodEmail
//            phone
            essentialAgree = insuranceArgument.essentialAgree
            optionAgree = insuranceArgument.optionAgree

            if (contactMethodEmail.isNotEmpty() && contactMethodMsg.isNotEmpty()) {
                contactType = resourceProvider.getString(R.string.insurance_charge_contact_both)
            } else if (contactMethodEmail.isNotEmpty()) {
                contactType = resourceProvider.getString(R.string.insurance_charge_contact_email)
            } else if (contactMethodMsg.isNotEmpty()) {
                contactType = resourceProvider.getString(R.string.insurance_charge_contact_talk)
            }
        }
    }

    fun onClickFinishBtn() {
        emitEventFlow(InsuranceChargeCheckEvent.GoToFinishPage)
    }
}