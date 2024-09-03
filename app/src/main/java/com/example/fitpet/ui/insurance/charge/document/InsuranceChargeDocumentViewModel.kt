package com.example.fitpet.ui.insurance.charge.document

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeDocumentViewModel @Inject constructor(): BaseViewModel<InsuranceChargeDocumentPageState>() {

    private val _cameraPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _cameraPhotoUri: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)
    private val _detailPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _detailPhotoUri: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)

    override val uiState = InsuranceChargeDocumentPageState(
        cameraPhoto = _cameraPhoto.asStateFlow(),
        cameraPhotoUri = _cameraPhotoUri.asStateFlow(),
        detailPhoto = _detailPhoto.asStateFlow(),
        detailPhotoUri = _detailPhotoUri.asStateFlow()
    )

    fun onClickAddReceipt() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddReceiptBtn)
    }

    fun onClickAddDetail() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddDetailBtn)
    }

    fun getReceiptPhoto(photo: String, photoUri: Uri) {
        Timber.d("[사진] 영수증 -> Photo: $photo && URI: $photoUri")
        viewModelScope.launch {
            _cameraPhoto.update { photo }
        }
    }

    fun getDetailPhoto(photo: String, photoUri: Uri) {
        Timber.d("[사진] 세부 내역 -> Photo: $photo && URI: $photoUri")
        viewModelScope.launch {
            _detailPhoto.update { photo }
        }
    }
}