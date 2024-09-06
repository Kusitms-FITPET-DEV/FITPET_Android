package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class ChargeUploadResponse (
    @SerializedName("medicalExpensesUrl")
    val medicalExpensesUrl: String,
    @SerializedName("receiptUrl")
    val receiptUrl: String,
    @SerializedName("etcUrl")
    val etcUrl: String
)