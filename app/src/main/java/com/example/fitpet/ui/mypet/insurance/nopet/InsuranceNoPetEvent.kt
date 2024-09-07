package com.example.fitpet.ui.mypet.insurance.nopet

import com.example.fitpet.Event

sealed class InsuranceNoPetEvent: Event {
    data object GoToAddPet: InsuranceNoPetEvent()
    data object GoToConsult: InsuranceNoPetEvent()
}