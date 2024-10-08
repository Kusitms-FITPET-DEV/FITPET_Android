package com.example.fitpet.ui.insurance.charge.finish

import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeFinishViewModel @Inject constructor(): BaseViewModel<PageState.Default>() {

    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun onClickBackBtn() {
        emitEventFlow(InsuranceChargeFinishEvent.GoToMyPetPage)
    }

    fun onClickCompensationBtn() {
        emitEventFlow(InsuranceChargeFinishEvent.GoToCompensationPage)
    }
}