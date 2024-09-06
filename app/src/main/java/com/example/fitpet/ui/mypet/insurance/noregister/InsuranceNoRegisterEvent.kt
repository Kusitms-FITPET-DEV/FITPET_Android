package com.example.fitpet.ui.mypet.insurance.noregister

import com.example.fitpet.Event

sealed class InsuranceNoRegisterEvent: Event {
    data object GoToConsult: InsuranceNoRegisterEvent()
    data object OpenMyPetDialog: InsuranceNoRegisterEvent()
    data object ChangeRange: InsuranceNoRegisterEvent()
    data object ShowNothing: InsuranceNoRegisterEvent()
    data object FetchInsurance: InsuranceNoRegisterEvent()
}