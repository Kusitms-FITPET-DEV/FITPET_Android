package com.example.fitpet.model.domain.insurance.main

import com.example.fitpet.model.domain.PetType
import kotlinx.coroutines.flow.StateFlow

data class MyPet(
    var petType: PetType,
    var petName: String,
    var petAge: Int,
    var petBreed: String,
)
