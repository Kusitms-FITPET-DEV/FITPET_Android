package com.example.fitpet.ui.insurance.charge.document

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeDocumentViewModel @Inject constructor(): BaseViewModel<InsuranceChargeDocumentPageState>() {

    private val _receiptPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _receiptPhotoUri: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)
    private val _detailPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _detailPhotoUri: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)
    private val _etcPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _etcPhotoUri: MutableStateFlow<Uri> = MutableStateFlow(Uri.EMPTY)

    override val uiState = InsuranceChargeDocumentPageState(
        receiptPhoto = _receiptPhoto.asStateFlow(),
        receiptPhotoUri = _receiptPhotoUri.asStateFlow(),
        detailPhoto = _detailPhoto.asStateFlow(),
        detailPhotoUri = _detailPhotoUri.asStateFlow(),
        etcPhoto = _etcPhoto.asStateFlow(),
        etcPhotoUri = _etcPhotoUri.asStateFlow(),
        isBtnEnabled = combine(_receiptPhoto, _detailPhoto) { receipt, detail ->
            isGoNextValid(receipt, detail)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun onClickAddReceipt() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddReceiptBtn)
    }

    fun onClickAddDetail() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddDetailBtn)
    }

    fun onClickAddEtc() {
        emitEventFlow(InsuranceChargeDocumentEvent.ClickAddEtcBtn)
    }

    fun getPhoto(photo: String, photoUri: Uri, photoType: String) {
        Timber.d("[사진] -> type: $photoType && Photo: $photo && URI: $photoUri")

        viewModelScope.launch {
            when (photoType) {
                RECEIPT_PHOTO ->  _receiptPhoto.update { photo }
                DETAIL_PHOTO -> _detailPhoto.update { photo }
                ETC_PHOTO -> _etcPhoto.update { photo }
            }
        }
    }

    private fun isGoNextValid(receiptOpt: String, detailOpt: String): Boolean {
        return receiptOpt.isNotEmpty() && detailOpt.isNotEmpty()
    }

    companion object {
        const val RECEIPT_PHOTO = "RECEIPT_PHOTO"
        const val DETAIL_PHOTO = "DETAIL_PHOTO"
        const val ETC_PHOTO = "ETC_PHOTO"
    }
}