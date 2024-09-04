package com.example.fitpet.ui.insurance.charge.contact.agree

import androidx.lifecycle.viewModelScope
import com.example.fitpet.PageState
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        isSelectedChoice = _isSelectedChoice.asStateFlow()
    )

    fun onClickAgreeType(agreeType: String) {
        viewModelScope.launch {
            when(agreeType) {
                TYPE_ALL -> _isSelectedAll.update { !_isSelectedAll.value }
                TYPE_ESSENTIAL -> _isSelectedEssential.update { !_isSelectedEssential.value }
                TYPE_CHOICE -> _isSelectedChoice.update { !_isSelectedChoice.value }
            }
        }
    }

    companion object {
        const val TYPE_ALL = "ALL"
        const val TYPE_ESSENTIAL = "TYPE_ESSENTIAL"
        const val TYPE_CHOICE = "TYPE_CHOICE"
    }
}