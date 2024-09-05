package com.example.fitpet.ui.insurance.charge.check

import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.insurance.charge.finish.InsuranceChargeCheckEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeCheckViewModel @Inject constructor(): BaseViewModel<PageState.Default>() {

    override val uiState: PageState.Default
        get() = TODO("Not yet implemented")

    fun onClickFinishBtn() {
        emitEventFlow(InsuranceChargeCheckEvent.GoToFinishPage)
    }
}