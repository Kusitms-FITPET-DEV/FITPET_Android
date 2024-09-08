package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class CompensationResponse (
    @SerializedName("petName")
    val petName: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("compensationlist")
    val compensationlist: List<CompensationsItem>
) {
    data class CompensationsItem(
        @SerializedName("compensationType")
        val compensationType: String,
        @SerializedName("progress")
        val progress: String,
        @SerializedName("applyDate")
        val applyDate: String,
        @SerializedName("chargePerson")
        val chargePerson: String?,
        @SerializedName("receiveMoney")
        val receiveMoney: Int
    )
}
