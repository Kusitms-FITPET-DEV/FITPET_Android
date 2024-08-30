package com.example.fitpet.ui.mypet.insurance

import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<InsurancePageState>() {
    override val uiState: InsurancePageState
        get() = TODO("Not yet implemented")
}