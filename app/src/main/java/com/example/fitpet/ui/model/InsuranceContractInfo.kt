package com.example.fitpet.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InsuranceContractInfo(
    val contract: Contract? = null,
    val coverage: Coverage? = null
) : Parcelable {

    @Parcelize
    data class Contract(
        val contractor: String? = "",
        val insurant: String? = "",
        val startDate: String? = "",
        val endDate: String? = "",
        val updateCycle: String? = "",
        val insuranceFee: Int? = 0,
        val priceRate: String? = "",
        val payWay: String? = "",
        val bank: String? = "",
        val bankAccount: String? = "",
        val payCycle: String? = ""
    ) : Parcelable

    @Parcelize
    data class Coverage(
        val dailyTreatLimit: String? = "",
        val dailySurgeryCostLimit: String? = "",
        val yearReward: String? = "",
        val hipJointLimit: String? = "",
        val skinDisease: String? = "",
        val skinEtc: String? = "",
        val oralCoverage: String? = "",
        val dentalCoverage: String? = "",
        val inspectionCoverage: String? = "",
        val foreignObjectRemoval: String? = "",
        val selfBurden: String? = "",
        val compensationLiability: String? = "",
        val funeralAid: String? = ""
    ) : Parcelable
}