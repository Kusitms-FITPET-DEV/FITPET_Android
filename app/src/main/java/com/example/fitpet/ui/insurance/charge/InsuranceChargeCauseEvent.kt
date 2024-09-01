package com.example.fitpet.ui.insurance.charge

import com.example.fitpet.Event

sealed class InsuranceChargeCauseEvent: Event {
    data object ClickCalendar: InsuranceChargeCauseEvent()
}