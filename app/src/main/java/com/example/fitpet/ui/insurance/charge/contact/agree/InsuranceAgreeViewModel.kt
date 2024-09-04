package com.example.fitpet.ui.insurance.charge.contact.agree

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceAgreeViewModel @Inject constructor(): BaseViewModel<InsuranceAgreePageState>() {

    private val _isSelectedAll: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isSelectedEssential: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isSelectedChoice: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val uiState = InsuranceAgreePageState(
        isSelectedAll = _isSelectedAll.asStateFlow(),
        isSelectedEssential = _isSelectedEssential.asStateFlow(),
        isSelectedChoice = _isSelectedChoice.asStateFlow(),

        isAgreeBtnEnabled = _isSelectedEssential.map { essential ->
            isAgreeValid(essential)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    )

    fun onClickAgreeType(agreeType: String) {
        viewModelScope.launch {
            when(agreeType) {
                TYPE_ALL -> {
                    _isSelectedAll.update { !_isSelectedAll.value }
                    updateAgreeEachTypeWhenAll(_isSelectedEssential)
                    updateAgreeEachTypeWhenAll(_isSelectedChoice)
                }
                TYPE_ESSENTIAL -> { updateAgreeEachType(_isSelectedEssential) }
                TYPE_CHOICE -> { updateAgreeEachType(_isSelectedChoice) }
            }
            _isSelectedAll.update { updateAgreeAllType(_isSelectedEssential.value, _isSelectedChoice.value) }
        }
    }

    private fun isAgreeValid(essential: Boolean): Boolean = essential

    private fun updateAgreeAllType(essential: Boolean, choice: Boolean): Boolean = essential && choice

    private fun updateAgreeEachTypeWhenAll(agreeType: MutableStateFlow<Boolean>) {
        if (_isSelectedAll.value != agreeType.value) agreeType.update { !agreeType.value }
    }

    private fun updateAgreeEachType(agreeType: MutableStateFlow<Boolean>) {
        agreeType.update { !agreeType.value }
        if (_isSelectedAll.value) _isSelectedAll.update { !_isSelectedAll.value }
    }

    companion object {
        const val TYPE_ALL = "ALL"
        const val TYPE_ESSENTIAL = "TYPE_ESSENTIAL"
        const val TYPE_CHOICE = "TYPE_CHOICE"
    }
}