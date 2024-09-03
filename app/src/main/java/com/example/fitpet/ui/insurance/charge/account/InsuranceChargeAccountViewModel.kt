package com.example.fitpet.ui.insurance.charge.account

import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeAccountViewModel @Inject constructor(): BaseViewModel<InsuranceChargeAccountPageState>() {

    override val uiState = InsuranceChargeAccountPageState(
        accountOwner = MutableStateFlow(""),
        accountNumber = MutableStateFlow("")
    )

    fun onClickBankInput() {
        emitEventFlow(InsuranceChargeAccountEvent.ClickBankInput)
    }
}