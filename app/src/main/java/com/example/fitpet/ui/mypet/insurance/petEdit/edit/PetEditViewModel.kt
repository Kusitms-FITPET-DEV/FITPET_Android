package com.example.fitpet.ui.mypet.insurance.petEdit.edit

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.request.PetEditRequest
import com.example.fitpet.model.request.RegisterPetRequest
import com.example.fitpet.model.response.GetPetInsuranceResponse
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.ui.mypet.insurance.main.InsuranceMainEvent
import com.example.fitpet.ui.registration.petBreed.PetBreedPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PetEditViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petsRepository: PetsRepository
): BaseViewModel<PetEditPageState>() {
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)
    private val editFlow: MutableStateFlow<Unit?> = MutableStateFlow(null)
    private val deleteFlow: MutableStateFlow<Unit?> = MutableStateFlow(null)
    private val registerFlow: MutableStateFlow<Unit?> = MutableStateFlow(null)
    private val breedStateFlow: MutableStateFlow<PetType> = MutableStateFlow(PetType.DOG)

    override val uiState: PetEditPageState = PetEditPageState(
        insuranceSuggestionResponse = petInsuranceFlow.asStateFlow(),
        editResponse = editFlow.asStateFlow(),
        deleteResponse = deleteFlow.asStateFlow(),
        registerResponse = registerFlow.asStateFlow(),
        breed = breedStateFlow.asStateFlow()
    )

    fun updateFlow(){
        petInsuranceFlow.update { null }
        editFlow.update { null }
        deleteFlow.update { null }
        registerFlow.update { null }
        breedStateFlow.update { PetType.DOG }
    }


    fun onClickBtnBreed(newType: PetType) {
        if (breedStateFlow.value == newType) return

        viewModelScope.launch {
            breedStateFlow.update { newType }
        }
    }

    fun editPet(petId: Int, petName: String, petBreed: String, petDetailBreed: String, petBirth: String) {
        viewModelScope.launch {
            val request = PetEditRequest(
                petName = petName,
                breed = petDetailBreed,
                species = petBreed,
                birthYear = petBirth.toInt()
            )
            petsRepository.editPet(petId, request).collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    editFlow.update { response }
                    emitEventFlow(PetEditEvent.Saved)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun registerPet(petName: String, petBreed: String, petDetailBreed: String, petBirth: String) {
        viewModelScope.launch {
            val formattedPhone = ""
            val request = RegisterPetRequest(
                petName = petName,
                breed = petDetailBreed,
                species = petBreed,
                birthYear = petBirth.toInt(),
                phone = formattedPhone
            )
            petsRepository.registerPet(request).collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    editFlow.update { response }
                    emitEventFlow(PetEditEvent.Saved)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun deletePet(petId: Int) {
        viewModelScope.launch {
            petsRepository.deletePet(petId).collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    editFlow.update { response }
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun fetchPetInsuranceInfo(petId: Int, priceRate: String) {
        viewModelScope.launch {
            petsRepository.getPetMainInfo(priceRate, petId).collect { result ->
                result.onSuccess { petInsuranceResponse ->
                    petInsuranceFlow.update { petInsuranceResponse }
                    emitEventFlow(PetEditEvent.FetchPetData)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun updateBreed(pet: String){
        if(pet == "DOG"){
            breedStateFlow.update { PetType.DOG }
        }else breedStateFlow.update { PetType.CAT }
    }

}