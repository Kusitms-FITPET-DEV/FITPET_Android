package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeCheckPageState (
    val causeType: String = "",
    val hospitalVisitDate: String = "",
    val receiptUrl: String = "",
    val medicalExpensesUrl: String = "",
    val etcUrl: String = "",
    val accountOwner: String = "",
    val accountBank: String = "",
    val accountNumber: String = "",
    var contactType: String = "",
    var contactMethodMsg: String = "",
    var contactMethodEmail: String = "",
    val phone: String = "",
    val essentialAgree: Boolean = false,
    val optionAgree: Boolean = false
): PageState