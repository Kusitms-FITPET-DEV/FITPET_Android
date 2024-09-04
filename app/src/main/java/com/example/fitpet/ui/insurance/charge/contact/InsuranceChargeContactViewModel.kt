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

    override val uiState = InsuranceChargeContactPageState(
        isSelectedKaKao = _isSelectedKaKao.asStateFlow(),
        isSelectedEmail = _isSelectedEmail.asStateFlow(),

        isBtnEnabled = combine(_isSelectedKaKao, _isSelectedEmail) { kakao, email ->
            isGoNextValid(kakao, email)
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

    private fun isGoNextValid(isKakaoSelected: Boolean, isEmailSelected: Boolean): Boolean {
        return isKakaoSelected || isEmailSelected
    }

    companion object {
        const val METHOD_KAKAO = "KAKAO"
        const val METHOD_EMAIL = "EMAIL"
    }
}