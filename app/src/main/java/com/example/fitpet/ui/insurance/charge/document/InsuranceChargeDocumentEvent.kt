package com.example.fitpet.ui.insurance.charge.document

import com.example.fitpet.Event

sealed class InsuranceChargeDocumentEvent: Event {
    data object ClickAddReceiptBtn: InsuranceChargeDocumentEvent()
}