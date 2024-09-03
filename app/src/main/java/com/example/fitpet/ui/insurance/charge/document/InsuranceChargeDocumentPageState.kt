package com.example.fitpet.ui.insurance.charge.document

import android.net.Uri
import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeDocumentPageState(
    val receiptPhoto: StateFlow<String>,
    val receiptPhotoUri: StateFlow<Uri>,
    val detailPhoto: StateFlow<String>,
    val detailPhotoUri: StateFlow<Uri>,
    val etcPhoto: StateFlow<String>,
    val etcPhotoUri: StateFlow<Uri>,
) : PageState