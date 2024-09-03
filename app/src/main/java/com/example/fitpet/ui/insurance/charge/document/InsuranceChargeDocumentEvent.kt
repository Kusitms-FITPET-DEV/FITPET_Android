package com.example.fitpet.ui.insurance.charge.document

import com.example.fitpet.Event

sealed class InsuranceChargeDocumentEvent: Event {
    data object ClickAddReceiptBtn: InsuranceChargeDocumentEvent()
    data object ClickAddDetailBtn: InsuranceChargeDocumentEvent()
    data object ClickAddEtcBtn: InsuranceChargeDocumentEvent()
}