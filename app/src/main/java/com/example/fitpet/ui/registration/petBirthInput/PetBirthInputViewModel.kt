package com.example.fitpet.ui.registration.petBirthInput

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetBirthInputViewModel @Inject constructor(

) : BaseViewModel<PetBirthInputPageState>() {
    private val birthStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val isValidInputStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val uiState: PetBirthInputPageState = PetBirthInputPageState(
        birth = birthStateFlow.asStateFlow(),
        isValidInput = isValidInputStateFlow.asStateFlow()
    )

    fun onTextChanged(input: CharSequence) {
        val newValue = input.toString()
        viewModelScope.launch {
            birthStateFlow.update { newValue }
            isValidInputStateFlow.update { validInput(newValue) }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetBirthInputEvent.GoToContactInfoInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetBirthInputEvent.ShowSkipDialog)
    }

    fun validInput(input: String): Boolean {
        return input.length == 4
    }
}