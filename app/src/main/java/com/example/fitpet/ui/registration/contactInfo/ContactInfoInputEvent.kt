package com.example.fitpet.ui.registration.contactInfo

import com.example.fitpet.Event

sealed class ContactInfoInputEvent: Event {
    data object GoToMyPetInsurance: ContactInfoInputEvent()
    data object ShowSkipDialog: ContactInfoInputEvent()
}