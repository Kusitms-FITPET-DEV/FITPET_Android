package com.example.fitpet.ui.insurance.charge.document

import com.example.fitpet.Event

sealed class InsuranceChargeDocumentEvent: Event {
    data object ClickAddReceiptBtn: InsuranceChargeDocumentEvent()
    data object ClickAddDetailBtn: InsuranceChargeDocumentEvent()
    data object ClickAddEtcBtn: InsuranceChargeDocumentEvent()
    data object ClickDeleteReceiptBtn: InsuranceChargeDocumentEvent()
    data object ClickDeleteDetailBtn: InsuranceChargeDocumentEvent()
    data object ClickDeleteEtcBtn: InsuranceChargeDocumentEvent()
    data object GoToAccountPage: InsuranceChargeDocumentEvent()
    data object UploadChargeImg: InsuranceChargeDocumentEvent()
}