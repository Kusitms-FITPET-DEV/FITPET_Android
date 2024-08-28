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

    override val uiState: PetBirthInputPageState = PetBirthInputPageState(
        birth = birthStateFlow.asStateFlow()
    )

    fun onTextChanged(input: CharSequence) {
        viewModelScope.launch {
            birthStateFlow.update { input.toString() }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetBirthInputEvent.GoToContactInfoInput)
    }
}