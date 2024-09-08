package com.example.fitpet.ui.mypet.insurance.noregister

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.model.response.EstimateList
import com.example.fitpet.model.response.PetInfo
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.model.response.PetResponse
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputEvent
import com.example.fitpet.ui.registration.petDetailBreed.PetDetailBreedInputPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InsuranceNoRegisterViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petsRepository: PetsRepository
): BaseViewModel<InsuranceNoRegisterPageState>() {
    private val myPetFlow: MutableStateFlow<PetInfo?> = MutableStateFlow(null)
    private val priceStartFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val priceEndFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val percentRangeFlow: MutableStateFlow<Int> = MutableStateFlow(70)
    private val suggestionFlow: MutableStateFlow<List<EstimateList>> = MutableStateFlow<List<EstimateList>>(emptyList())
    private val openPercentBoxFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val nothingInsuranceFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)
    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)



    override val uiState: InsuranceNoRegisterPageState = InsuranceNoRegisterPageState(
        petInfo = myPetFlow.asStateFlow(),
        insurancePriceStart = priceStartFlow.asStateFlow(),
        insurancePriceEnd = priceEndFlow.asStateFlow(),
        insuranceRangePercent = percentRangeFlow.asStateFlow(),
        insuranceSuggestion = suggestionFlow.asStateFlow(),
        openPercentBox = openPercentBoxFlow.asStateFlow(),
        nothingInsurance = nothingInsuranceFlow.asStateFlow(),
        petCount = petResponseFlow.asStateFlow(),
        insuranceSuggestionResponse = petInsuranceFlow.asStateFlow()
    )

    // pet Info 설정
    fun updatePetInfo(pet: PetInfo){
        viewModelScope.launch {
            myPetFlow.update { pet }
        }
        Timber.d(myPetFlow.value?.name)
        Timber.d(myPetFlow.value?.breed)
        Timber.d(myPetFlow.value?.age.toString())
        Timber.d(myPetFlow.value?.species.toString())
    }

    // 보험 데이터 없을 시 UI
    fun showNothingUI(boolean: Boolean){
        viewModelScope.launch {
            nothingInsuranceFlow.update { boolean }
        }
    }


    // 퍼센트 박스 설정
    fun updateBoxStatus(boolean: Boolean){
        viewModelScope.launch {
            openPercentBoxFlow.update { boolean }
        }
    }

    fun updatePercent(input: Int) {
        viewModelScope.launch {
            percentRangeFlow.update { input }
        }
    }

    fun updatePriceStart(input: Int) {
        viewModelScope.launch {
            priceStartFlow.update { input }
        }
    }

    fun updatePriceEnd(input: Int) {
        viewModelScope.launch {
            priceEndFlow.update { input }
        }
    }

    fun onClickBtnConsult() {
        emitEventFlow(InsuranceNoRegisterEvent.GoToConsult)
    }

    fun onClickPercent(percent : Int){
        when(percent){
            PERCENT_70-> updatePercent(PERCENT_70)
            PERCENT_80-> updatePercent(PERCENT_80)
            PERCENT_90-> updatePercent(PERCENT_90)
        }
        emitEventFlow(InsuranceNoRegisterEvent.ChangeRange)
    }

    fun onClickBtnPercentTab() {
        updateBoxStatus(!openPercentBoxFlow.value)
    }

    //포맷팅한 보험료 값 반환
    fun getFormattedPriceStart(): String? {
        val priceStart = priceStartFlow.value
        Timber.d(NumberFormat.getNumberInstance(Locale.US).format(priceStart))
        return if (priceStart == 0) {
            ""
        } else {
            NumberFormat.getNumberInstance(Locale.US).format(priceStart)
        }
    }

    fun onClickDialog(){
        emitEventFlow(InsuranceNoRegisterEvent.OpenMyPetDialog)
    }


    fun getFirstPet(): Int?{
        return petResponseFlow.value?.petList?.get(0)?.petId
    }

    fun getFormattedPriceEnd(): String? {
        val priceEnd = priceEndFlow.value
        Timber.d(NumberFormat.getNumberInstance(Locale.US).format(priceEnd))
        return if (priceEnd == 0) {
            ""
        } else {
            NumberFormat.getNumberInstance(Locale.US).format(priceEnd)
        }
    }

    fun loadPetData() {
        viewModelScope.launch {
            petsRepository.getPetAllMainInfo().collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    petResponseFlow.update { response }
                    updatePetInfo(response.petList.get(0))
                    emitEventFlow(InsuranceNoRegisterEvent.FetchPetData)
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
                    suggestionFlow.update { petInsuranceResponse.estimateList }
                    priceEndFlow.update { petInsuranceResponse.maxInsuranceFee }
                    priceStartFlow.update { petInsuranceResponse.minInsuranceFee }
                    emitEventFlow(InsuranceNoRegisterEvent.UpdateUI)
                    if(petInsuranceResponse.estimateList.isEmpty()){
                        emitEventFlow(InsuranceNoRegisterEvent.ShowNothing)
                    }else {
                        emitEventFlow(InsuranceNoRegisterEvent.FetchInsurance)
                    }
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }


    companion object {
        const val PERCENT_70 = 70
        const val PERCENT_80 = 80
        const val PERCENT_90 = 90
    }
}