package com.example.fitpet.ui.registration.petName

import com.example.fitpet.Event

sealed class PetNameInputEvent: Event {
    data object GoToPetBreedInput: PetNameInputEvent()
    data object ShowSkipDialog: PetNameInputEvent()
}