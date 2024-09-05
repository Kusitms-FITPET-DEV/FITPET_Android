package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.PageState

data class InsuranceChargeCheckPageState (
    val targetName: String = "",
    val causeType: String = "",
    val hospitalVisitDate: String = "",
    val receiptUrl: String = "",
    val medicalExpensesUrl: String = "",
    val etcUrl: String = "",
    var accountOwner: String = "",
    var accountBank: String = "",
    var accountNumber: String = "",
    var contactType: String = "",
    var contactMethodMsg: String = "",
    var contactMethodEmail: String = "",
    val phone: String = "",
    val essentialAgree: Boolean = false,
    val optionAgree: Boolean = false
): PageState