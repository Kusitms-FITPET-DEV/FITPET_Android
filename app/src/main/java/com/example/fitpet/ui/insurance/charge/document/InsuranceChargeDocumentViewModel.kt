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
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeDocumentViewModel @Inject constructor(): BaseViewModel<InsuranceChargeDocumentPageState>() {

    private val _receiptPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _detailPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _etcPhoto: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeDocumentPageState(
        receiptPhoto = _receiptPhoto.asStateFlow(),
        detailPhoto = _detailPhoto.asStateFlow(),
        etcPhoto = _etcPhoto.asStateFlow(),

        isBtnEnabled = combine(_receiptPhoto, _detailPhoto) { receipt, detail ->
            isGoNextValid(receipt, detail)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun onClickAddPhotoBtn(photoType: String) {
        when (photoType) {
            RECEIPT_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickAddReceiptBtn)
            DETAIL_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickAddDetailBtn)
            ETC_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickAddEtcBtn)
        }
    }

    fun onClickDeletePhotoBtn(photoType: String) {
        when (photoType) {
            RECEIPT_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickDeleteReceiptBtn)
            DETAIL_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickDeleteDetailBtn)
            ETC_PHOTO -> emitEventFlow(InsuranceChargeDocumentEvent.ClickDeleteEtcBtn)
        }
    }

    fun onClickNextBtn() {
        emitEventFlow(InsuranceChargeDocumentEvent.GoToAccountPage)
    }

    private fun updatePhoto(photoType: String, photo: String) {
        when (photoType) {
            RECEIPT_PHOTO -> _receiptPhoto.update { photo }
            DETAIL_PHOTO -> _detailPhoto.update { photo }
            ETC_PHOTO -> _etcPhoto.update { photo }
        }
    }

    fun getPhoto(photo: String, photoUri: Uri, photoType: String) {
        viewModelScope.launch {
            updatePhoto(photoType, photo)
        }
    }

    fun deletePhoto(photoType: String) {
        viewModelScope.launch {
            updatePhoto(photoType, DELETE_PHOTO)
        }
    }

    private fun isGoNextValid(receiptOpt: String, detailOpt: String): Boolean {
        return receiptOpt.isNotEmpty() && detailOpt.isNotEmpty()
    }

    companion object {
        const val RECEIPT_PHOTO = "RECEIPT_PHOTO"
        const val DETAIL_PHOTO = "DETAIL_PHOTO"
        const val ETC_PHOTO = "ETC_PHOTO"
        const val DELETE_PHOTO = ""
    }
}