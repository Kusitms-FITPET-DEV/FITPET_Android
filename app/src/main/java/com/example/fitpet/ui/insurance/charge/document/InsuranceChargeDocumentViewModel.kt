package com.example.fitpet.ui.insurance.charge.document

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.ChargeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeDocumentViewModel @Inject constructor(
    private val chargeRepository: ChargeRepository
): BaseViewModel<InsuranceChargeDocumentPageState>() {

    private val _receiptPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _detailPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private val _etcPhoto: MutableStateFlow<String> = MutableStateFlow("")
    private lateinit var _receiptPhotoPart: MultipartBody.Part
    private lateinit var _detailPhotoPart: MultipartBody.Part
    private var _etcPhotoPart: MultipartBody.Part? = null

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

    fun uploadChargeImgToApi() {
        val etcPart = _etcPhotoPart ?: createEmptyPart(ETC_PHOTO_PART)

        viewModelScope.launch {
            chargeRepository.uploadChargeImg(_receiptPhotoPart, _detailPhotoPart, etcPart).collect { result ->
                resultResponse(result, {
                    Timber.d("[보험 청구] 이미지 등록 -> $result")
                    goToNextPage()
                })
            }
        }
    }

    private fun createMultipartBodyPart(file: File, partName: String): MultipartBody.Part {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }

    private fun createEmptyPart(partName: String): MultipartBody.Part {
        val emptyBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
        return MultipartBody.Part.createFormData(partName, "", emptyBody)
    }

    private fun goToNextPage() {
        emitEventFlow(InsuranceChargeDocumentEvent.GoToAccountPage)
    }

    private fun updatePhoto(photoType: String, photo: String) {
        when (photoType) {
            RECEIPT_PHOTO -> _receiptPhoto.update { photo }
            DETAIL_PHOTO -> _detailPhoto.update { photo }
            ETC_PHOTO -> _etcPhoto.update { photo }
        }
    }

    private fun updatePhotoFileToMulti(file: File, photoType: String) {
        when (photoType) {
            RECEIPT_PHOTO -> _receiptPhotoPart = createMultipartBodyPart(file, RECEIPT_PHOTO_PART)
            DETAIL_PHOTO -> _detailPhotoPart = createMultipartBodyPart(file, DETAIL_PHOTO_PART)
            ETC_PHOTO -> _etcPhotoPart = createMultipartBodyPart(file, ETC_PHOTO_PART)
        }
    }

    fun getPhoto(photo: String, file: File, photoType: String) {
        viewModelScope.launch {
            updatePhoto(photoType, photo)
            updatePhotoFileToMulti(file, photoType)
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
        const val RECEIPT_PHOTO_PART = "receipt"
        const val DETAIL_PHOTO_PART = "medicalExpenses"
        const val ETC_PHOTO_PART = "etc"
    }
}