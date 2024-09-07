package com.example.fitpet.ui.insurance.compensation

import com.example.fitpet.Event

sealed class InsuranceCompensationEvent: Event {
    data object GoBackPage: InsuranceCompensationEvent()
}