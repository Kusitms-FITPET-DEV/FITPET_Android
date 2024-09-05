package com.example.fitpet.ui.registration.petDetailBreed

import com.example.fitpet.PageState
import com.example.fitpet.model.response.SearchPetBreedResponse
import kotlinx.coroutines.flow.StateFlow

data class PetDetailBreedInputPageState(
    val detailBreed: StateFlow<String>,
    val selectedDetailBreed: StateFlow<String>,
    val searchedBreedList: StateFlow<List<String>>,
    val selectedBreed: StateFlow<String>
): PageState