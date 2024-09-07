package com.example.fitpet.ui.insurance.charge.check

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.ChargeRepository
import com.example.fitpet.model.request.ChargeInsuranceRequest
import com.example.fitpet.ui.model.InsuranceCharge
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCheckViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val chargeRepository: ChargeRepository
) : BaseViewModel<InsuranceChargeCheckPageState>() {

    override val uiState = InsuranceChargeCheckPageState()

    fun setInsuranceData(insuranceArgument: InsuranceCharge) {
        with (uiState) {
            petId = insuranceArgument.petId
            targetName = insuranceArgument.targetName
            causeType = insuranceArgument.causeType
            hospitalVisitDate = insuranceArgument.hospitalVisitDate
            hospitalVisitDateBindingFormat = formatDate(insuranceArgument.hospitalVisitDate)
            receiptUrl = insuranceArgument.receiptUrl
            medicalExpensesUrl = insuranceArgument.medicalExpensesUrl
            etcUrl = insuranceArgument.etcUrl
            accountBank = insuranceArgument.accountBank
            accountNumber = insuranceArgument.accountNumber
            contactMethodMsg = insuranceArgument.contactMethodMsg
            contactMethodEmail = insuranceArgument.contactMethodEmail
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
        viewModelScope.launch {
            with(uiState) {
                val request = ChargeInsuranceRequest(
                    causeType,
                    hospitalVisitDate,
                    receiptUrl,
                    medicalExpensesUrl,
                    etcUrl,
                    accountOwner,
                    accountBank,
                    accountNumber,
                    contactMethodMsg,
                    contactMethodEmail,
                    phone,
                    essentialAgree,
                    optionAgree
                )

                chargeRepository.chargeInsurance(petId, request).collect {
                    resultResponse(it, {
                        Timber.d("[보험금 청구] 서버통신 성공")
                        goToFinishPage() })
                }
            }
        }
    }

    private fun goToFinishPage() {
        emitEventFlow(InsuranceChargeCheckEvent.GoToFinishPage)
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy. MM. dd", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
}