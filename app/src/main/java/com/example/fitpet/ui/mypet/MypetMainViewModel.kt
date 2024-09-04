package com.example.fitpet.ui.mypet

import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.onboarding.OnBoardingPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MypetMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<MypetMainPageState>() {
    override val uiState = MypetMainPageState()

    fun onInsuranceNoPetFragmentClick() {
        emitEventFlow(MypetMainEvent.GoToAddPetButtonClick)
    }
}