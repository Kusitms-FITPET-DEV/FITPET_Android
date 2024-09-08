package com.example.fitpet.ui.mypet.insurance.petEdit

import com.example.fitpet.Event

sealed class PetBottomSheetEvent: Event {
    data object FetchPetData : PetBottomSheetEvent()
}