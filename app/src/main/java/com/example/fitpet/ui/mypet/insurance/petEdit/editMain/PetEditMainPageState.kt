package com.example.fitpet.ui.mypet.insurance.petEdit.editMain

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import kotlinx.coroutines.flow.StateFlow

data class PetEditMainPageState (
    val isSelected: StateFlow<Boolean>,
    val editPet: StateFlow<Int>,
    val clickedPet: StateFlow<PetInfo?>,
    val petData: StateFlow<PetResponse?>
): PageState