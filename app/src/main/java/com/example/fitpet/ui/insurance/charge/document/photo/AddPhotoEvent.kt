package com.example.fitpet.ui.insurance.charge.document.photo

import com.example.fitpet.Event

sealed class AddPhotoEvent: Event {
    data object ClickCamera: AddPhotoEvent()
    data object ClickGallery: AddPhotoEvent()
}