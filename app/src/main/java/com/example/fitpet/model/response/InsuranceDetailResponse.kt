package com.example.fitpet.model.response

import com.google.gson.annotations.SerializedName

data class InsuranceDetailResponse(
    @SerializedName("contract")
    val contract: InsuranceContract,
    @SerializedName("coverage")
    val coverage: InsuranceCoverage
) {
    data class InsuranceContract(
        @SerializedName("contractor")
        val contractor: String,
        @SerializedName("insurant")
        val insurant: String,
        @SerializedName("startDate")
        val startDate: String,
        @SerializedName("endDate")
        val endDate: String,
        @SerializedName("updateCycle")
        val updateCycle: String,
        @SerializedName("insuranceFee")
        val insuranceFee: Int,
        @SerializedName("priceRate")
        val priceRate: String,
        @SerializedName("payWay")
        val payWay: String,
        @SerializedName("bank")
        val bank: String,
        @SerializedName("bankAccount")
        val bankAccount: String,
        @SerializedName("payCycle")
        val payCycle: String
    )

    data class InsuranceCoverage(
        @SerializedName("dailyTreatLimit")
        val dailyTreatLimit: String,
        @SerializedName("dailySurgeryCostLimit")
        val dailySurgeryCostLimit: String,
        @SerializedName("yearReward")
        val yearReward: String,
        @SerializedName("hipJointLimit")
        val hipJointLimit: String,
        @SerializedName("skinDisease")
        val skinDisease: String,
        @SerializedName("skinEtc")
        val skinEtc: String,
        @SerializedName("oralCoverage")
        val oralCoverage: String,
        @SerializedName("dentalCoverage")
        val dentalCoverage: String,
        @SerializedName("inspectionCoverage")
        val inspectionCoverage: String,
        @SerializedName("foreignObjectRemoval")
        val foreignObjectRemoval: String,
        @SerializedName("selfBurden")
        val selfBurden: String,
        @SerializedName("compensationLiability")
        val compensationLiability: String,
        @SerializedName("funeralAid")
        val funeralAid: String
    )
}