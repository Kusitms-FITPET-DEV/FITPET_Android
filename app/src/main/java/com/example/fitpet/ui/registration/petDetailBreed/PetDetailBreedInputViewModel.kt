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
import javax.inject.Inject

@HiltViewModel
class PetDetailBreedInputViewModel @Inject constructor(
    private val petsRepository: PetsRepository
) : BaseViewModel<PetDetailBreedInputPageState>() {
    private val _breedSearchQuery = MutableStateFlow("")

    private val detailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDetailBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val searchedBreedListStateFlow: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val selectedBreedStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val uiState: PetDetailBreedInputPageState = PetDetailBreedInputPageState(
        detailBreed = detailBreedStateFlow.asStateFlow(),
        selectedDetailBreed = selectedDetailBreedStateFlow.asStateFlow(),
        searchedBreedList = searchedBreedListStateFlow.asStateFlow(),
        selectedBreed = selectedBreedStateFlow.asStateFlow()
    )

    init {
        viewModelScope.launch {
            _breedSearchQuery
                .debounce(500L)
                .filter { it.isNotBlank() }
                .collect { keyword ->
                    searchBreeds(keyword)
                }
        }
    }

//    fun onTextChanged(input: CharSequence) {
//        viewModelScope.launch {
//            val keyword = input.toString()
//            detailBreedStateFlow.update { keyword }
//            petsRepository.searchDetailBreed(keyword).collect {
//                resultResponse(it, ::onSuccessSearchDetailBreed)
//            }
//        }
//    }

    fun onTextChanged(input: CharSequence) {
        viewModelScope.launch {
            _breedSearchQuery.emit(input.toString())
        }
    }

    private suspend fun searchBreeds(keyword: String) {
        petsRepository.searchDetailBreed(keyword).collect {
            resultResponse(it, ::onSuccessSearchDetailBreed)
        }
    }

    private fun onSuccessSearchDetailBreed(value: SearchPetBreedResponse) {
        viewModelScope.launch {
            searchedBreedListStateFlow.update { value.searchedBreeds }
            delay(500)
            emitEventFlow(PetDetailBreedInputEvent.ShowDropdown)
        }
    }

    fun onBreedSelected(breed: String) {
        viewModelScope.launch {
            selectedBreedStateFlow.update { breed }
        }
    }

    fun onClickBtnNext() {
        emitEventFlow(PetDetailBreedInputEvent.GoToPetBirthInput)
    }

    fun onClickSkip() {
        emitEventFlow(PetDetailBreedInputEvent.ShowSkipDialog)
    }
}