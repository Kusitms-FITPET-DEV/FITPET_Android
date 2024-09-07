package com.example.fitpet.ui.mypet.insurance.noregister

import com.example.fitpet.Event

sealed class InsuranceRecommendEvent: Event {
    data object FetchInsurance: InsuranceRecommendEvent()
    data object GoToConsult: InsuranceRecommendEvent()
}