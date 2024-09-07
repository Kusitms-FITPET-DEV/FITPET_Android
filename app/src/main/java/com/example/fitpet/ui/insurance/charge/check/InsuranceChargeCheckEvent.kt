package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.Event

sealed class InsuranceChargeCheckEvent: Event {
    data object GoToFinishPage: InsuranceChargeCheckEvent()
}