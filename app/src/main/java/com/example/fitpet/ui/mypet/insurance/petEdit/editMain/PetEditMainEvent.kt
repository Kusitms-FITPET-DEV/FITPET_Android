package com.example.fitpet.ui.mypet.insurance.petEdit.editMain

import com.example.fitpet.Event

sealed class PetEditMainEvent: Event {
    data object FetchPetData : PetEditMainEvent()
}