package com.example.fitpet.ui.mypet.insurance.petEdit.edit

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import kotlinx.coroutines.flow.StateFlow

data class PetEditPageState (
    var insuranceSuggestionResponse: StateFlow<PetInsuranceResponse?>,
    var editResponse: StateFlow<Unit?>,
    var deleteResponse: StateFlow<Unit?>,
    var registerResponse: StateFlow<Unit?>,
    val breed: StateFlow<PetType>
): PageState