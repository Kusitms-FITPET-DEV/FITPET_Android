package com.example.fitpet.ui.insurance.info

import com.example.fitpet.Event

sealed class InsuranceContractCheckEvent: Event {
    data object GoBackPage: InsuranceContractCheckEvent()
}