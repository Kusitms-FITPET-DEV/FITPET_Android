package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class GetPetInsuranceResponse(
    @SerializedName("insuranceCompany") val insuranceCompany: String,
    @SerializedName("insuranceName") val insuranceName: String,
    @SerializedName("discountList") val discountList: List<String>,
    @SerializedName("positiveList") val positiveList: List<String>,
    @SerializedName("beforeDiscountFee") val beforeDiscountFee: Int,
    @SerializedName("afterDiscountFee") val afterDiscountFee: Int,
    @SerializedName("dailyTreatLimit") val dailyTreatLimit: String,
    @SerializedName("dailySurgeryCostLimit") val dailySurgeryCostLimit: String,
    @SerializedName("yearReward") val yearReward: String,
    @SerializedName("hipJointLimit") val hipJointLimit: String,
    @SerializedName("skinDisease") val skinDisease: String,
    @SerializedName("skinEtc") val skinEtc: String,
    @SerializedName("oralCoverage") val oralCoverage: String,
    @SerializedName("dentalCoverage") val dentalCoverage: String,
    @SerializedName("inspectionCoverage") val inspectionCoverage: String,
    @SerializedName("foreignObjectRemoval") val foreignObjectRemoval: String,
    @SerializedName("selfBurden") val selfBurden: String,
    @SerializedName("compensationLiability") val compensationLiability: String,
    @SerializedName("funeralAid") val funeralAid: String
)