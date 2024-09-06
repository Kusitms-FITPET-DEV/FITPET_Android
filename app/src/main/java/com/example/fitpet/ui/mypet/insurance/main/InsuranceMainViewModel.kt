package com.example.fitpet.ui.mypet.insurance.main

import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<InsuranceMainPageState>() {
    override val uiState= InsuranceMainPageState()
}