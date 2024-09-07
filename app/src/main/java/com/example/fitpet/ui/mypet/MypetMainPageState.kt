package com.example.fitpet.ui.mypet

import com.example.fitpet.PageState
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import kotlinx.coroutines.flow.StateFlow

data class MypetMainPageState(
    var insuranceSuggestion: StateFlow<PetInsuranceResponse?>,
    var petCount: StateFlow<PetResponse?>,
    var petId: StateFlow<Int?>,
    var priceId: StateFlow<Int?>,
    var company: StateFlow<String?>
): PageState