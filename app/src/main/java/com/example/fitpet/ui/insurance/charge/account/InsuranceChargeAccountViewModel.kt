package com.example.fitpet.ui.insurance.charge.account

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceChargeAccountViewModel @Inject constructor(): BaseViewModel<InsuranceChargeAccountPageState>() {

    var isFirstSelection = true
    private val _accountOwner: MutableStateFlow<String> = MutableStateFlow("")
    private val _accountBank: MutableStateFlow<String> = MutableStateFlow("")
    private val _accountNumber: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeAccountPageState(
        accountOwner = _accountOwner.asStateFlow(),
        accountNumber = _accountNumber.asStateFlow(),
        accountBank = _accountBank.asStateFlow(),

        isBtnEnabled = combine(_accountOwner, _accountBank, _accountNumber) { owner, bank, number ->
            isGoNextValid(owner, bank, number)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun onTextChanged(edtType: String, newText: CharSequence) {
        viewModelScope.launch {
            when (edtType) {
                ACCOUNT_OWNER -> _accountOwner.update { newText.toString() }
                ACCOUNT_NUMBER -> _accountNumber.update { newText.toString() }
            }
        }
    }

    fun getAccountBank(bank: String) {
        viewModelScope.launch {
            _accountBank.update { bank }
        }
    }

    private fun isGoNextValid(accountOwner: String, accountBank: String, accountNumber: String): Boolean {
        return accountOwner.isNotEmpty() && accountBank.isNotEmpty() && accountNumber.isNotEmpty()
    }

    fun onClickNextBtn() {
        emitEventFlow(InsuranceChargeAccountEvent.GoToContactPage)
    }

    companion object {
        const val ACCOUNT_OWNER = "ACCOUNT_OWNER"
        const val ACCOUNT_NUMBER = "ACCOUNT_NUMBER"
        const val BANK_SPINNER_DEFAULT = 0
    }
}