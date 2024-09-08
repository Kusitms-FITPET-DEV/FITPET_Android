package com.example.fitpet.ui.mypet.insurance.main

import com.example.fitpet.Event

sealed class InsuranceMainEvent: Event {
    data object GoToConsult: InsuranceMainEvent()
    data object GoToCharge: InsuranceMainEvent()
//    data object GoToContractCheck: InsuranceMainEvent()
    data object GoToCompensationCheck: InsuranceMainEvent()
    data object OpenMyPetDialog: InsuranceMainEvent()
    data object UpdatePetInfo: InsuranceMainEvent()
    data object UpdateInsuranceInfo: InsuranceMainEvent()

}