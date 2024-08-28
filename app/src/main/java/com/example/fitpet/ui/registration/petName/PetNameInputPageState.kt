package com.example.fitpet.ui.registration.petName

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class PetNameInputPageState(
    val petName: StateFlow<String>
) : PageState