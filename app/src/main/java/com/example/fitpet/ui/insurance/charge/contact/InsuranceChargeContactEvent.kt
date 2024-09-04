package com.example.fitpet.ui.insurance.charge.contact

import com.example.fitpet.Event

sealed class InsuranceChargeContactEvent: Event {
    data object ClickNextAgreeBtn: InsuranceChargeContactEvent()
}