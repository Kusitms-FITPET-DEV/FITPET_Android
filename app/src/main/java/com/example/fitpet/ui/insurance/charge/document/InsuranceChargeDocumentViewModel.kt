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

    private val _selectedPhoto: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeDocumentPageState(
        selectedPhoto = _selectedPhoto.asStateFlow()
    )

    fun onClickAddReceipt() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddReceiptBtn)
    }

    fun getSelectedPhoto(photo: String, photoUri: Uri) {
        Timber.d("[사진] -> Photo: $photo && URI: $photoUri")
        viewModelScope.launch {
            _selectedPhoto.update { photo }
        }
    }
}