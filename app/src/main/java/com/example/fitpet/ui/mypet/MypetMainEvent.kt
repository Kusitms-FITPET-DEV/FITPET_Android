package com.example.fitpet.ui.mypet

import com.example.fitpet.Event

sealed class MypetMainEvent: Event {
    data object GoToAddPetButtonClick : MypetMainEvent()
    data object GoToInsuranceInfoCheckPage: MypetMainEvent()
}