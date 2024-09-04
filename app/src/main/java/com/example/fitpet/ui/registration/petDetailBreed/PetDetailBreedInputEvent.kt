package com.example.fitpet.ui.registration.petDetailBreed

import com.example.fitpet.Event

sealed class PetDetailBreedInputEvent: Event {
    data object GoToPetBirthInput: PetDetailBreedInputEvent()
    data object ShowSkipDialog: PetDetailBreedInputEvent()
}