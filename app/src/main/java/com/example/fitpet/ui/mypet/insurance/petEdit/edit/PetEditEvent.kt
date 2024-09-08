package com.example.fitpet.ui.mypet.insurance.petEdit.edit

import com.example.fitpet.Event

sealed class PetEditEvent: Event {
    data object FetchPetData : PetEditEvent()
    data object GoToDelete : PetEditEvent()
    data object GoToSave : PetEditEvent()
    data object GoToBack : PetEditEvent()
    data object Saved : PetEditEvent()
}