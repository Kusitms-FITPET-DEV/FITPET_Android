package com.example.fitpet.ui.splash

import com.example.fitpet.Event

sealed class SplashEvent: Event {
    data object GoToOnBoarding: SplashEvent()
}