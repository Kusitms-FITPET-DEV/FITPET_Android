package com.example.fitpet.ui.mypet.life

import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LifeViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<LifePageState>() {
    override val uiState = LifePageState()
}