package com.example.fitpet.model

data class CompensationListItem (
    val id: Int,
    val compensationType: String,
    val progress: String,
    val applyDate: String,
    val chargePerson: String?,
    val receiveMoney: Int,
    val firstProgress: Int,
    val secondProgress: Int,
    val thirdProgress: Int,
    val fourthProgress: Int
)