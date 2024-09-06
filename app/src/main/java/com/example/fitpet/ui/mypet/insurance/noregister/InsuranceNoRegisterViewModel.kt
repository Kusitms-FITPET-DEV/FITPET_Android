package com.example.fitpet.ui.mypet.insurance.noregister

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.InsuranceSuggestion
import com.example.fitpet.model.domain.insurance.main.MyPet
import com.example.fitpet.model.response.EstimateList
import com.example.fitpet.model.response.PetInsuranceResponse
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
    private val myPetFlow: MutableStateFlow<MyPet> = MutableStateFlow(MyPet(PetType.DOG,"",0,""))
    private val priceStartFlow: MutableStateFlow<Int> = MutableStateFlow(1000)
    private val priceEndFlow: MutableStateFlow<Int> = MutableStateFlow(10000)
    private val percentRangeFlow: MutableStateFlow<Int> = MutableStateFlow(70)
    private val suggestionFlow: MutableStateFlow<List<EstimateList>> = MutableStateFlow<List<EstimateList>>(emptyList())
    private val openPercentBoxFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val nothingInsuranceFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)



    override val uiState: InsuranceNoRegisterPageState = InsuranceNoRegisterPageState(
        petInfo = myPetFlow.asStateFlow(),
        insurancePriceStart = priceStartFlow.asStateFlow(),
        insurancePriceEnd = priceEndFlow.asStateFlow(),
        insuranceRangePercent = percentRangeFlow.asStateFlow(),
        insuranceSuggestion = suggestionFlow.asStateFlow(),
        openPercentBox = openPercentBoxFlow.asStateFlow(),
        nothingInsurance = nothingInsuranceFlow.asStateFlow()
    )

    // pet Info 설정
    fun updatePetInfo(pet: MyPet){
        viewModelScope.launch {
            myPetFlow.update { pet }
        }
        Timber.d(myPetFlow.value.petName)
        Timber.d(myPetFlow.value.petBreed)
        Timber.d(myPetFlow.value.petAge.toString())
        Timber.d(myPetFlow.value.petType.toString())
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

    fun getFormattedPriceEnd(): String? {
        val priceEnd = priceEndFlow.value
        Timber.d(NumberFormat.getNumberInstance(Locale.US).format(priceEnd))
        return if (priceEnd == 0) {
            ""
        } else {
            NumberFormat.getNumberInstance(Locale.US).format(priceEnd)
        }
    }

    fun fetchPetInsuranceInfo(petId: Int, priceRate: String) {
        viewModelScope.launch {
            petsRepository.getPetMainInfo(priceRate, petId).collect { result ->
                result.onSuccess { petInsuranceResponse ->
                    // Update both pet info and estimate list
                    petInsuranceFlow.update { petInsuranceResponse }
                    suggestionFlow.update { petInsuranceResponse.estimateList }
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    // Example to access Pet info like name, species, etc.
    fun getPetName(): String? {
        return petInsuranceFlow.value?.name
    }

    fun getFormattedMaxInsuranceFee(): String {
        val maxInsuranceFee = petInsuranceFlow.value?.maxInsuranceFee ?: 0
        return NumberFormat.getNumberInstance(Locale.US).format(maxInsuranceFee)
    }


    companion object {
        const val PERCENT_70 = 70
        const val PERCENT_80 = 80
        const val PERCENT_90 = 90
    }
}