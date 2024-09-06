package com.example.fitpet.ui.registration.petBirthInput

import com.example.fitpet.Event

sealed class PetBirthInputEvent: Event {
    data object GoToContactInfoInput: PetBirthInputEvent()
    data object ShowSkipDialog: PetBirthInputEvent()
    data object InvalidFutureBirthYear: PetBirthInputEvent()
    data object IneligibleDueToAge: PetBirthInputEvent()
}