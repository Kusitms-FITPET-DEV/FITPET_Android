package com.example.fitpet.ui.registration.petBreed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.domain.PetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetBreedViewModel @Inject constructor() : BaseViewModel<PetBreedPageState>() {
    private val breedStateFlow: MutableStateFlow<PetType> = MutableStateFlow(PetType.DOG)

    override val uiState: PetBreedPageState = PetBreedPageState(
        breed = breedStateFlow.asStateFlow()
    )

    fun onClickBtnBreed(newType: PetType) {
        if (breedStateFlow.value == newType) return

        viewModelScope.launch {
            breedStateFlow.update { newType }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetBreedEvent.GoToPetBreedDetail)
    }
}