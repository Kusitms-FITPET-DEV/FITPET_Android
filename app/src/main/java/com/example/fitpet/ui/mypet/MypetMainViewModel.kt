package com.example.fitpet.ui.mypet

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypetMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petsRepository: PetsRepository
): BaseViewModel<MypetMainPageState>() {
    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)

    override val uiState = MypetMainPageState(
        petCount = petResponseFlow.asStateFlow(),
        insuranceSuggestion = petInsuranceFlow.asStateFlow()
    )

    fun onInsuranceNoPetFragmentClick() {
        emitEventFlow(MypetMainEvent.GoToAddPetButtonClick)
    }

    fun checkCount(): Int{
        if(petResponseFlow.value?.petCount==0){
            return 0
        }else return 1
    }

    fun getFirstPet(): Int?{
        return petResponseFlow.value?.petList?.get(0)?.petId
    }

    fun checkInsurance(): Int{
        if(petInsuranceFlow.value?.insurance == true) {
            return 0
        }else return 1
    }

    fun goToNoPet() {
        emitEventFlow(MypetMainEvent.GoToNoPet)
    }

    fun goToMain() {
        emitEventFlow(MypetMainEvent.GoToMain)
    }

    fun goToNoRegister() {
        emitEventFlow(MypetMainEvent.GoToNoRegister)
    }

    fun fetchPetData() {
        emitEventFlow(MypetMainEvent.FetchPetData)
    }

    fun fetchInsuranceData() {
        emitEventFlow(MypetMainEvent.FetchInsuranceData)
    }



    // Pet 정보를 불러오는 함수
    fun loadPetData() {
        viewModelScope.launch {
            petsRepository.getPetAllMainInfo().collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    petResponseFlow.update { response }
                    fetchPetData()
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
                    // Update both pet info and estimate list
                    petInsuranceFlow.update { petInsuranceResponse }
                    fetchInsuranceData()
                    Timber.d("여기야! ")
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }
}