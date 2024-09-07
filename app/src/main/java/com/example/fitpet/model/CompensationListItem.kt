package com.example.fitpet.model

data class CompensationListItem (
    val compensationType: String,
    val progress: String,
    val applyDate: String,
    val chargePerson: String?,
    val receiveMoney: Int
)