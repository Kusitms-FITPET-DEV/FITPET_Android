package com.example.fitpet.ui.registration.petBreed

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.PetType
import kotlinx.coroutines.flow.StateFlow

data class PetBreedPageState(
    val breed: StateFlow<PetType>
): PageState