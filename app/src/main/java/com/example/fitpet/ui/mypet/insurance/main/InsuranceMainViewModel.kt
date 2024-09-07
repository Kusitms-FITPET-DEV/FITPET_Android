package com.example.fitpet.ui.mypet.insurance.main

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterEvent
import com.example.fitpet.ui.mypet.insurance.noregister.InsuranceNoRegisterPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InsuranceMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petsRepository: PetsRepository
): BaseViewModel<InsuranceMainPageState>() {
    private val myPetFlow: MutableStateFlow<PetInfo?> = MutableStateFlow(null)
    private val insurancePriceFlow: MutableStateFlow<Int> = MutableStateFlow(10000)
    private val nowMonthFlow: MutableStateFlow<Int> = MutableStateFlow(8)
    private val insuranceInfoFlow: MutableStateFlow<EstimateList?> = MutableStateFlow(null)
    private val insuranceImgFlow: MutableStateFlow<Int> = MutableStateFlow(R.drawable.ic_db_v3)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)
    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)


    override val uiState: InsuranceMainPageState = InsuranceMainPageState(
        petInfo = myPetFlow.asStateFlow(),
        insurancePrice = insurancePriceFlow.asStateFlow(),
        nowMonth = nowMonthFlow.asStateFlow(),
        insuranceInfo = insuranceInfoFlow.asStateFlow(),
        insuranceImg = insuranceImgFlow.asStateFlow(),
        petCount = petResponseFlow.asStateFlow(),
        insuranceSuggestionResponse = petInsuranceFlow.asStateFlow()
    )

    // nowMonth 설정
    @RequiresApi(Build.VERSION_CODES.O)
    fun setMonth(){
        val currentMonth = LocalDate.now().monthValue
        viewModelScope.launch {
            nowMonthFlow.update { currentMonth }
        }
    }

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

    fun updatePrice(input: Int) {
        viewModelScope.launch {
            insurancePriceFlow.update { input }
        }
    }

    fun updateInsuranceInfo(insurance: EstimateList) {
        viewModelScope.launch {
            insuranceInfoFlow.update { insurance }
        }
    }

    fun updateInsuranceImg(resource: Int) {
        viewModelScope.launch {
            insuranceImgFlow.update { resource }
        }
    }

    fun onClickBtnConsult() {
        emitEventFlow(InsuranceMainEvent.GoToConsult)
    }

    fun onClickChargeBtn() {
        emitEventFlow(InsuranceMainEvent.GoToCharge)
    }

    fun onClickCompensationCheckBtn() {
        emitEventFlow(InsuranceMainEvent.GoToCompensationCheck)
    }

//    fun onClickContractCheckBtn() {
//        emitEventFlow(InsuranceMainEvent.GoToContractCheck)
//    }

    //다이얼로그 닫을 시 펫 정보 및 보험 정보 업데이트 필요
    fun onDismissPetDialog() {
        emitEventFlow(InsuranceMainEvent.UpdatePetInfo)
        emitEventFlow(InsuranceMainEvent.UpdateInsuranceInfo) // 이미지도 같이 변경해줘야 함.
    }

    //포맷팅한 보험료 값 반환
    fun getFormattedPrice(): String? {
        val priceStart = insurancePriceFlow.value
        Timber.d(NumberFormat.getNumberInstance(Locale.US).format(priceStart))
        return if (priceStart == 0) {
            ""
        } else {
            NumberFormat.getNumberInstance(Locale.US).format(priceStart)
        }
    }

    fun loadPetData() {
        viewModelScope.launch {
            petsRepository.getPetAllMainInfo().collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    petResponseFlow.update { response }
                    updatePetInfo(response.petList.get(0))
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
                    emitEventFlow(InsuranceMainEvent.UpdateInsuranceInfo)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun getFirstPet(): Int?{
        return petResponseFlow.value?.petList?.get(0)?.petId
    }
}