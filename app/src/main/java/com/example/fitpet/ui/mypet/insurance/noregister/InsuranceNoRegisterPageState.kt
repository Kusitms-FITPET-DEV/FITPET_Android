package com.example.fitpet.ui.mypet.insurance.noregister

import com.example.fitpet.PageState
import com.example.fitpet.model.OnBoardingItemVo
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.model.response.EstimateList
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import kotlinx.coroutines.flow.StateFlow

data class InsuranceNoRegisterPageState(
    var petInfo: StateFlow<PetInfo?>,
    var insurancePriceStart: StateFlow<Int>,
    var insurancePriceEnd: StateFlow<Int>,
    var insuranceRangePercent: StateFlow<Int>,
    var insuranceSuggestion: StateFlow<List<EstimateList>>,
    var openPercentBox: StateFlow<Boolean>,
    var nothingInsurance: StateFlow<Boolean>,
    var petCount: StateFlow<PetResponse?>,
    var insuranceSuggestionResponse: StateFlow<PetInsuranceResponse?>
): PageState