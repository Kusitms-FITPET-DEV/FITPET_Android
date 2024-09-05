package com.example.fitpet.ui.registration.contactInfo

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactInfoInputViewModel @Inject constructor(

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
        emitEventFlow(ContactInfoInputEvent.GoToMyPetInsurance)
    }

    fun onClickSkip() {
        emitEventFlow(ContactInfoInputEvent.ShowSkipDialog)
    }
}