package com.example.fitpet.ui.insurance.charge.document

import android.net.Uri
import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeDocumentPageState(
    val cameraPhoto: StateFlow<String>,
    val cameraPhotoUri: StateFlow<Uri>,
    val detailPhoto: StateFlow<String>,
    val detailPhotoUri: StateFlow<Uri>,
) : PageState