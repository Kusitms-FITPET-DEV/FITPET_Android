package com.example.fitpet.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InsuranceCharge (
    val causeType: String = "",
    val hospitalVisitDate: String = "",
    val receiptUrl: String = "",
    val medicalExpensesUrl: String = "",
    val etcUrl: String = "",
    val accountOwner: String = "",
    val accountBank: String = "",
    val accountNumber: String = "",
    val contactMethodMsg: String = "",
    val contactMethodEmail: String = "",
    val phone: String = "",
    val essentialAgree: Boolean = false,
    val optionAgree: Boolean = false
): Parcelable