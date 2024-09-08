package com.example.fitpet.ui.login

import com.example.fitpet.Event

sealed class KakaoLoginEvent: Event {
    data object OnClickKakaoLogin: KakaoLoginEvent()
    data object GoToPetNameInput: KakaoLoginEvent()
    data object GoToInsuranceMain: KakaoLoginEvent()
}