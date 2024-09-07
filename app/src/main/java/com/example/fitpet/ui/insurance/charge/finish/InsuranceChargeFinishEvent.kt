package com.example.fitpet.ui.insurance.charge.finish

import com.example.fitpet.Event

sealed class InsuranceChargeFinishEvent: Event {
    data object GoToMyPetPage: InsuranceChargeFinishEvent()
    data object GoToCompensationPage: InsuranceChargeFinishEvent()
}