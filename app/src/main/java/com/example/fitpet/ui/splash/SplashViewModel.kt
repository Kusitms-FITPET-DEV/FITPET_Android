package com.example.fitpet.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

): BaseViewModel<PageState.Default>() {

    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun processSplash() {
        viewModelScope.launch {
            delay(3000)
            goToOnBoarding()
        }
    }

    private fun goToOnBoarding() {
        emitEventFlow(SplashEvent.GoToOnBoarding)
    }
}