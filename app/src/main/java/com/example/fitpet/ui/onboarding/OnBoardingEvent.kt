package com.example.fitpet.ui.onboarding

import com.example.fitpet.Event

sealed class OnBoardingEvent: Event {
    data object GoToPermissionSettings: OnBoardingEvent()
}