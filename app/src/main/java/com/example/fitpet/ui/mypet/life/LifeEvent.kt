package com.example.fitpet.ui.mypet.life

import com.example.fitpet.Event

sealed class LifeEvent: Event {
    data object GetNewQuote: LifeEvent()
    data object ShowCorrectDialog: LifeEvent()
    data object ShowFalseDialog: LifeEvent()
    data object GoToHospitalRecordDetail: LifeEvent()
    data object GoToFaq: LifeEvent()
}