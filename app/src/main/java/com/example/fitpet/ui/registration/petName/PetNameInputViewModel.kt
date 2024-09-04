package com.example.fitpet.ui.registration.petName

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetNameInputViewModel @Inject constructor(

) : BaseViewModel<PetNameInputPageState>() {
    private val petNameStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState: PetNameInputPageState = PetNameInputPageState(
        petName = petNameStateFlow.asStateFlow()
    )

    fun onTextChanged(newText: CharSequence) {
        viewModelScope.launch {
            petNameStateFlow.update { newText.toString() }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetNameInputEvent.GoToPetBreedInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetNameInputEvent.ShowSkipDialog)
    }
}