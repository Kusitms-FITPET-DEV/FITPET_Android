package com.example.fitpet.ui.registration.contactInfo

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class ContactInfoInputPageState(
    val contactInfo: StateFlow<String>,
    val isMaxLength: StateFlow<Boolean>
): PageState