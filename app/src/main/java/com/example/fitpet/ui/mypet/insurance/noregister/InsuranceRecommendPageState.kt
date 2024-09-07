package com.example.fitpet.ui.mypet.insurance.noregister

import com.example.fitpet.PageState
import com.example.fitpet.model.response.GetPetInsuranceResponse
import kotlinx.coroutines.flow.StateFlow

data class InsuranceRecommendPageState(
    var insuranceSuggestionResponse: StateFlow<GetPetInsuranceResponse?>,
    var isOpened: StateFlow<Boolean>
): PageState