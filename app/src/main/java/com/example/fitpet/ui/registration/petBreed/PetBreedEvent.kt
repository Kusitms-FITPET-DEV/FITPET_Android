package com.example.fitpet.ui.registration.petBreed

import com.example.fitpet.Event

sealed class PetBreedEvent: Event {
    data object GoToPetBreedDetail: PetBreedEvent()
}