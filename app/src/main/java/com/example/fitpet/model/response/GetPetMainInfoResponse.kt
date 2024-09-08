package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class EstimateList(
    @SerializedName("priceId") val priceId: Int = 0,
    @SerializedName("insuranceCompany") val insuranceCompany: String = "",
    @SerializedName("insuranceName") val insuranceName: String = "",
    @SerializedName("insuranceFee") val insuranceFee: Int = 0
)

data class PetInsuranceResponse(
    @SerializedName("birthYear") val birthYear: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("species") val species: String = "",
    @SerializedName("birthYear") val birthYear: Int = 0,
    @SerializedName("age") val age: Int = 0,
    @SerializedName("breed") val breed: String = "",
    @SerializedName("maxInsuranceFee") val maxInsuranceFee: Int = 0,
    @SerializedName("minInsuranceFee") val minInsuranceFee: Int = 0,
    @SerializedName("resultCount") val resultCount: Int = 0,
    @SerializedName("estimateList") val estimateList: List<EstimateList> = emptyList(),
    @SerializedName("insurance") val insurance: Boolean = false
)