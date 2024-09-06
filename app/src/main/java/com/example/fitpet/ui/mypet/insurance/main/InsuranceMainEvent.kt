package com.example.fitpet.ui.mypet.insurance.main

import com.example.fitpet.Event

sealed class InsuranceMainEvent: Event {
    data object GoToConsult: InsuranceMainEvent()
    data object GoToCharge: InsuranceMainEvent()
    data object GoToCheck: InsuranceMainEvent()
    data object OpenMyPetDialog: InsuranceMainEvent()
}