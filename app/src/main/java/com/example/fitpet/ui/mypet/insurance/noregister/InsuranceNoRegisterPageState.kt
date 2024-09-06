package com.example.fitpet.ui.mypet.insurance.noregister

import com.example.fitpet.PageState
import com.example.fitpet.model.OnBoardingItemVo
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.model.response.EstimateList
import kotlinx.coroutines.flow.StateFlow

data class InsuranceNoRegisterPageState(
    var petInfo: StateFlow<MyPet>,
    var insurancePriceStart: StateFlow<Int>,
    var insurancePriceEnd: StateFlow<Int>,
    var insuranceRangePercent: StateFlow<Int>,
    var insuranceSuggestion: StateFlow<List<EstimateList>>,
    var openPercentBox: StateFlow<Boolean>,
    var nothingInsurance: StateFlow<Boolean>
): PageState