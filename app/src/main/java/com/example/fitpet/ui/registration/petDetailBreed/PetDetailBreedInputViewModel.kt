package com.example.fitpet.ui.registration.petDetailBreed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailBreedInputViewModel @Inject constructor(

) : BaseViewModel<PetDetailBreedInputPageState>() {
    private val detailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDetailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState: PetDetailBreedInputPageState = PetDetailBreedInputPageState(
        detailBreed = detailBreedStateFlow.asStateFlow(),
        selectedDetailBreed = selectedDetailBreedStateFlow.asStateFlow()
    )

    fun onTextChanged(input: CharSequence) {
        viewModelScope.launch {
            detailBreedStateFlow.update { input.toString() }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetDetailBreedInputEvent.GoToPetBirthInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetDetailBreedInputEvent.ShowSkipDialog)
    }
}