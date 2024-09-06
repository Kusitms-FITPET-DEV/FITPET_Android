package com.example.fitpet.ui.mypet.insurance.main

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.insurance.main.MyPet
import kotlinx.coroutines.flow.StateFlow

data class InsuranceMainPageState(
    var petInfo: StateFlow<MyPet>,
    var insurancePrice: StateFlow<Int>,
    var nowMonth: StateFlow<Int>,
): PageState