package com.example.fitpet.ui.registration.petDetailBreed

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class PetDetailBreedInputPageState(
    val detailBreed: StateFlow<String>,
    val selectedDetailBreed: StateFlow<String>
): PageState