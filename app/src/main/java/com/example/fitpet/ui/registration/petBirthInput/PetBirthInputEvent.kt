package com.example.fitpet.ui.registration.petBirthInput

import com.example.fitpet.Event

sealed class PetBirthInputEvent: Event {
    data object GoToContactInfoInput: PetBirthInputEvent()
}