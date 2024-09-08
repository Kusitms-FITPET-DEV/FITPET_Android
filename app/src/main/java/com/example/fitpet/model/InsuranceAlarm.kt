package com.example.fitpet.model

data class InsuranceAlarm (
    val historyId: Int? = 0,
    val progress: String? = "",
    val time: String? = "",
    val confirmed: Boolean? = false
)