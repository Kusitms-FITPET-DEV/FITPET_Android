package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.PageState

data class InsuranceChargeCheckPageState (
    var targetName: String = "",
    var causeType: String = "",
    var hospitalVisitDate: String = "",
    var receiptUrl: String = "",
    var medicalExpensesUrl: String = "",
    var etcUrl: String = "",
    var accountOwner: String = "",
    var accountBank: String = "",
    var accountNumber: String = "",
    var contactType: String = "",
    var contactMethodMsg: String = "",
    var contactMethodEmail: String = "",
    var phone: String = "",
    var essentialAgree: Boolean = false,
    var optionAgree: Boolean = false
): PageState