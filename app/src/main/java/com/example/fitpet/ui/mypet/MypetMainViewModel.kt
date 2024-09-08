package com.example.fitpet.ui.mypet

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.AlarmRepository
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.InsuranceAlarm
import com.example.fitpet.model.response.AlarmHistoryResponse
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
    private val petsRepository: PetsRepository,
    private val alarmRepository: AlarmRepository
): BaseViewModel<MypetMainPageState>() {

    private val _insuranceAlarmList: MutableStateFlow<List<InsuranceAlarm>> = MutableStateFlow(emptyList())
    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)
    private val petIdFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val priceIdFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val companyFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    override val uiState = MypetMainPageState(
        insuranceAlarmList = _insuranceAlarmList.asStateFlow(),
        petCount = petResponseFlow.asStateFlow(),
        insuranceSuggestion = petInsuranceFlow.asStateFlow(),
        petId = petIdFlow.asStateFlow(),
        priceId = priceIdFlow.asStateFlow(),
        company = companyFlow.asStateFlow()
    )

    fun setInsuranceAlarmList() {
        Timber.d("[서버통신 테스트] -> 확인 ")
        viewModelScope.launch {
            alarmRepository.getAlarmHistoryList().collect { result ->
                resultResponse(result, { data ->
                    Timber.d("[서버통신 테스트] -> $data")
                    _insuranceAlarmList.update { setAlarmList(data) }
                })
            }
        }
    }

    private fun setAlarmList(data: List<AlarmHistoryResponse.HistoryItem>): List<InsuranceAlarm> {
        val tempList = ArrayList<InsuranceAlarm>()

        for (i in data.indices) {
            with (data[i]) {
                tempList.add(InsuranceAlarm(historyId, progress, time, confirmed))
            }
        }

        return tempList
    }

    fun onInsuranceNoPetFragmentClick() {
        emitEventFlow(MypetMainEvent.GoToAddPetButtonClick)
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

    fun onClickContractCheckBtn() {
        emitEventFlow(MypetMainEvent.GoToInsuranceInfoCheckPage)
    }
    fun reset(){
        petIdFlow.update { null }
        priceIdFlow.update { null }
        companyFlow.update { null }
    }

    fun onInsuranceRecommendFragmentClick(petId: Int, priceId: Int, company:String) {
        reset()
        petIdFlow.update { petId }
        priceIdFlow.update { priceId }
        companyFlow.update { company }
        emitEventFlow(MypetMainEvent.GoToRecommendButtonClick)

    }

    fun checkCount(): Int{
        if(petResponseFlow.value?.petCount==0){
            return 0
        }else return 1
    }

    fun onClickCompensationBtn() {
        emitEventFlow(MypetMainEvent.GoTOCompensationPage)
    }

    fun onClickInsuranceCharge() {
        emitEventFlow(MypetMainEvent.GoToInsuranceChargePage)

    }
}