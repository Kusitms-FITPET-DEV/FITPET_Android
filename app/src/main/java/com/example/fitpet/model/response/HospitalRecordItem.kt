package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class HospitalRecordItem(
    @SerializedName("journalId")
    val journalId: Long,
    @SerializedName("insuranceCompany")
    val insuranceCompany: String,
    @SerializedName("insuranceName")
    val insuranceName: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>
)
