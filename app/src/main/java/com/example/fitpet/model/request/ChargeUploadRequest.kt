package com.example.fitpet.model.request

import com.google.gson.annotations.SerializedName

data class ChargeUploadRequest (
    @SerializedName("receipt")
    val receipt: String,
    @SerializedName("medicalExpenses")
    val medicalExpenses: String,
    @SerializedName("etc")
    val etc: String
)