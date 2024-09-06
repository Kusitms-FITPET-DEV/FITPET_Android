package com.example.fitpet.ui.registration.contactInfo

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.request.RegisterPetRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactInfoInputViewModel @Inject constructor(
    private val petsRepository: PetsRepository
) : BaseViewModel<ContactInfoInputPageState>() {
    private val contactInfoStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState: ContactInfoInputPageState = ContactInfoInputPageState(
        contactInfo = contactInfoStateFlow.asStateFlow()
    )

    fun onTextChanged(input: CharSequence) {
        viewModelScope.launch {
            contactInfoStateFlow.update { input.toString() }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(ContactInfoInputEvent.RegisterPet)
    }

    fun onClickSkip() {
        emitEventFlow(ContactInfoInputEvent.ShowSkipDialog)
    }

    fun registerPet(petName: String, petBreed: String, petDetailBreed: String, petBirth: String) {
        viewModelScope.launch {
            val request = RegisterPetRequest(
                petName = petName,
                breed = petBreed,
                species = petDetailBreed,
                birthYear = petBirth.toInt(),
                phone = uiState.contactInfo.value
            )
            petsRepository.registerPet(request).collect {
                resultResponse(it, { goToMyPetInsurance() })
            }
        }
    }

    private fun goToMyPetInsurance() {
        emitEventFlow(ContactInfoInputEvent.GoToMyPetInsurance)
    }
}