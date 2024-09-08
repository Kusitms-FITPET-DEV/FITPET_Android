package com.example.fitpet.ui.insurance.compensation

import com.example.fitpet.PageState
import com.example.fitpet.model.CompensationListItem
import kotlinx.coroutines.flow.StateFlow

data class InsuranceCompensationPageState (
    val petName: StateFlow<String>,
    val startDate: StateFlow<String>,
    val endDate: StateFlow<String>,
    val compensationItemList: StateFlow<List<CompensationListItem>>
): PageState