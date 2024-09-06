package com.example.fitpet.ui.insurance.charge.finish

import com.example.fitpet.Event

sealed class InsuranceChargeCheckEvent: Event {
    data object GoToFinishPage: InsuranceChargeCheckEvent()
}