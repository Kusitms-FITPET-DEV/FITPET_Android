package com.example.fitpet.ui.insurance.charge.account

import com.example.fitpet.Event

sealed class InsuranceChargeAccountEvent: Event {
    data object GoToContactPage: InsuranceChargeAccountEvent()
}