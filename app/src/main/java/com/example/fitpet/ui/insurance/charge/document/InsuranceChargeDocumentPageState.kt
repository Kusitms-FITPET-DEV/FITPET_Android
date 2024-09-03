package com.example.fitpet.ui.insurance.charge.document

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeDocumentPageState(
    val selectedPhoto: StateFlow<String>
) : PageState