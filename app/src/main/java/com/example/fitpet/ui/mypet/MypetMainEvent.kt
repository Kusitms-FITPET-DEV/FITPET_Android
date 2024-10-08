package com.example.fitpet.ui.mypet

import com.example.fitpet.Event

sealed class MypetMainEvent: Event {
    data object GoToAddPetButtonClick : MypetMainEvent()
    data object GoToInsuranceInfoCheckPage: MypetMainEvent()
    data object GoTOCompensationPage: MypetMainEvent()
    data object GoToInsuranceChargePage: MypetMainEvent()
    data object GoToNoPet : MypetMainEvent()
    data object GoToNoRegister : MypetMainEvent()
    data object GoToMain : MypetMainEvent()
    data object FetchPetData : MypetMainEvent()
    data object FetchInsuranceData : MypetMainEvent()
    data object GoToRecommendButtonClick: MypetMainEvent()
    data object ClickedPet : MypetMainEvent()
    data object ClickedEditPet : MypetMainEvent()
    data object ClickedEditPetMain : MypetMainEvent()
}