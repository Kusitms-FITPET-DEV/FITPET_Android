package com.example.fitpet.ui.registration.petDetailBreed

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.response.SearchPetBreedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetDetailBreedInputViewModel @Inject constructor(
    private val petsRepository: PetsRepository
) : BaseViewModel<PetDetailBreedInputPageState>() {
    private val detailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDetailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val searchedBreedListStateFlow: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val selectedBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState: PetDetailBreedInputPageState = PetDetailBreedInputPageState(
        detailBreed = detailBreedStateFlow.asStateFlow(),
        selectedDetailBreed = selectedDetailBreedStateFlow.asStateFlow(),
        searchedBreedList = searchedBreedListStateFlow.asStateFlow(),
        selectedBreed = selectedBreedStateFlow.asStateFlow(),
    )

    fun onTextChanged(input: CharSequence) {
        viewModelScope.launch {
            val keyword = input.toString()
            detailBreedStateFlow.update { keyword }
            petsRepository.searchDetailBreed(keyword).collect {
                resultResponse(it, ::onSuccessSearchDetailBreed)
            }
        }
    }

    fun onCatTextChanged(input: CharSequence) {
        viewModelScope.launch {
            selectedDetailBreedStateFlow.update { input.toString() }
        }
    }

    private fun onSuccessSearchDetailBreed(value: SearchPetBreedResponse) {
        viewModelScope.launch {
            searchedBreedListStateFlow.update { value.searchedBreeds }
            emitEventFlow(PetDetailBreedInputEvent.ShowDropdown)
        }
    }

    fun onBreedSelected(breed: String) {
        viewModelScope.launch {
            selectedDetailBreedStateFlow.update { breed }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetDetailBreedInputEvent.GoToPetBirthInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetDetailBreedInputEvent.ShowSkipDialog)
    }

    fun setPetBreed(breed: String) {
        viewModelScope.launch {
            selectedBreedStateFlow.update { breed }
        }
    }
}