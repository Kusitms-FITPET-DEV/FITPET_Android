package com.example.fitpet.ui.insurance.charge.contact

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
class InsuranceChargeContactViewModel @Inject constructor() : BaseViewModel<InsuranceChargeContactPageState>() {

    private val _isSelectedKaKao: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isSelectedEmail: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _emailInput: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState = InsuranceChargeContactPageState(
        isSelectedKaKao = _isSelectedKaKao.asStateFlow(),
        isSelectedEmail = _isSelectedEmail.asStateFlow(),
        emailInput = _emailInput.asStateFlow(),

        isBtnEnabled = combine(_isSelectedKaKao, _isSelectedEmail, _emailInput) { kakao, email, emailInput ->
            isGoNextValid(kakao, email, emailInput)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun onClickContactMethod(contactType: String) {
        viewModelScope.launch {
            when (contactType) {
                METHOD_KAKAO -> _isSelectedKaKao.update { !_isSelectedKaKao.value }
                METHOD_EMAIL -> _isSelectedEmail.update { !_isSelectedEmail.value }
            }
        }
    }

    fun onTextChanged(newText: CharSequence) {
        viewModelScope.launch {
            _emailInput.update { newText.toString() }
        }
    }

    private fun isGoNextValid(isKakaoSelected: Boolean, isEmailSelected: Boolean, emailInput: String): Boolean {
        return isKakaoSelected || (isEmailSelected && emailInput.isNotEmpty())
    }

    companion object {
        const val METHOD_KAKAO = "KAKAO"
        const val METHOD_EMAIL = "EMAIL"
    }
}