package com.example.fitpet.ui.insurance.charge.document

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeDocumentPageState(
    val receiptPhoto: StateFlow<String>,
    val detailPhoto: StateFlow<String>,
    val etcPhoto: StateFlow<String>,
    val isBtnEnabled: StateFlow<Boolean>
) : PageState