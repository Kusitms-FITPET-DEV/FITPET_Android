package com.example.fitpet.model.request

import com.google.gson.annotations.SerializedName

data class ChargeInsuranceRequest (
    @SerializedName("type")
    val type: String,
    @SerializedName("visitTime")
    val visitTime: String,
    @SerializedName("receiptUrl")
    val receiptUrl: String,
    @SerializedName("medicalExpensesUrl")
    val medicalExpensesUrl: String,
    @SerializedName("etcUrl")
    val etcUrl: String,
    @SerializedName("accountHolder")
    val accountHolder: String,
    @SerializedName("bank")
    val bank: String,
    @SerializedName("account")
    val account: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("essentialAgree")
    val essentialAgree: Boolean,
    @SerializedName("optionalAgree")
    val optionalAgree: Boolean
)