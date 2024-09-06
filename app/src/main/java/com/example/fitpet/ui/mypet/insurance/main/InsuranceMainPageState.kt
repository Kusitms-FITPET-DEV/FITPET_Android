package com.example.fitpet.ui.mypet.insurance.main

import android.graphics.drawable.Drawable
import com.example.fitpet.PageState
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.model.response.PetResponse
import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

data class InsuranceMainPageState(
    var petInfo: StateFlow<MyPet>,
    var insuranceInfo: StateFlow<InsuranceSuggestion>,
    var insurancePrice: StateFlow<Int>,
    var insuranceImg: StateFlow<Int>,
    var nowMonth: StateFlow<Int>,
    var petCount: StateFlow<PetResponse?>
): PageState