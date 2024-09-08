package com.example.fitpet.ui.mypet.insurance.petEdit

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.model.response.PetResponse
import com.example.fitpet.ui.mypet.MypetMainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetBottomSheetViewModel @Inject constructor(private val petsRepository: PetsRepository): BaseViewModel<PetBottomSheetPageState>() {

    private val isSelectedPet: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val editPetFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)
    private val clickedPetFlow: MutableStateFlow<PetInfo?> = MutableStateFlow(null)

    override val uiState = PetBottomSheetPageState(
        isSelected = isSelectedPet.asStateFlow(),
        petData = petResponseFlow.asStateFlow(),
        clickedPet = clickedPetFlow.asStateFlow(),
        editPet = editPetFlow.asStateFlow()
    )

    fun onClickAgreeType(agreeType: String) {
        viewModelScope.launch {

        }
    }

    fun updatePetInfo(pet: PetInfo){
        viewModelScope.launch {
            clickedPetFlow.update { pet }
        }
    }

    fun updateEditPetId(pet: Int){
        viewModelScope.launch {
            editPetFlow.update { pet }
        }
    }

    fun emitClickedPet(){
        emitEventFlow(MypetMainEvent.ClickedPet)
    }

    fun emitEditedPet(){
        emitEventFlow(MypetMainEvent.ClickedEditPet)
    }



    // Pet 정보를 불러오는 함수
    fun loadPetData() {
        viewModelScope.launch {
            petsRepository.getPetAllMainInfo().collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    petResponseFlow.update { response }
                    emitEventFlow(PetBottomSheetEvent.FetchPetData)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

}