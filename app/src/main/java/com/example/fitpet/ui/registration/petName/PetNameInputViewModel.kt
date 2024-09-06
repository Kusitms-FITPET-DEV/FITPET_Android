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
    private val isInvalidInputStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val isLengthValidStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val uiState: PetNameInputPageState = PetNameInputPageState(
        petName = petNameStateFlow.asStateFlow(),
        isInvalidInput = isInvalidInputStateFlow.asStateFlow(),
        isLengthValid = isLengthValidStateFlow.asStateFlow()
    )

    fun onTextChanged(newText: CharSequence) {
        viewModelScope.launch {
            val petName = newText.toString()
            petNameStateFlow.update { petName }

            isInvalidInputStateFlow.update { validatePetName(petName) }
            isLengthValidStateFlow.update { validateLength(petName) }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetNameInputEvent.GoToPetBreedInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetNameInputEvent.ShowSkipDialog)
    }

    private fun validatePetName(petName: String): Boolean {
        val specialCharPattern = "[^가-힣]".toRegex()
        if (specialCharPattern.containsMatchIn(petName)) {
            return true
        }

        val consonantsOnly = "^[ㄱ-ㅎㅏ-ㅣ]+$".toRegex()
        if (consonantsOnly.matches(petName)) {
            return true
        }

        if (!petName.matches("^[가-힣]+$".toRegex())) {
            return true
        }

        if (petName.contains(" ")) {
            return true
        }

        return false
    }

    private fun validateLength(petName: String): Boolean {
        val consonantsOnly = "^[ㄱ-ㅎㅏ-ㅣ]+$".toRegex()

        if (petName.isEmpty()) return false

        return !(petName.length == 1 && consonantsOnly.matches(petName))
    }
}