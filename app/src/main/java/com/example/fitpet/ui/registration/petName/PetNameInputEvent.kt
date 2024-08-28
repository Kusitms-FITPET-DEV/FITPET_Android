package com.example.fitpet.ui.registration.petName

import com.example.fitpet.Event

sealed class PetNameEvent: Event {
    data object GoToPetBreedInput: PetNameEvent()
}