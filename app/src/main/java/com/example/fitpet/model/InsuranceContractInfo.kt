package com.example.fitpet.model

data class InsuranceContractInfo (
    val contract: Contract?,
    val coverage: Coverage?
) {
    data class Contract(
        val contractor: String,
        val insurant: String,
        val startDate: String,
        val endDate: String,
        val updateCycle: String,
        val insuranceFee: Int,
        val priceRate: String,
        val payWay: String,
        val bank: String,
        val bankAccount: String,
        val payCycle: String
    )

    data class Coverage(
        val dailyTreatLimit: String,
        val dailySurgeryCostLimit: String,
        val yearReward: String,
        val hipJointLimit: String,
        val skinDisease: String,
        val skinEtc: String,
        val oralCoverage: String,
        val dentalCoverage: String,
        val inspectionCoverage: String,
        val foreignObjectRemoval: String,
        val selfBurden: String,
        val compensationLiability: String,
        val funeralAid: String
    )
}