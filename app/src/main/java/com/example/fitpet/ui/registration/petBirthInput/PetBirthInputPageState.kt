package com.example.fitpet.ui.registration.petBirthInput

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class PetBirthInputPageState(
    val birth: StateFlow<String>
): PageState