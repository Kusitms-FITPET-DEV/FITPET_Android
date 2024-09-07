package com.example.fitpet.ui.mypet

import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.mypet.insurance.main.InsuranceMainEvent
import com.example.fitpet.ui.onboarding.OnBoardingPageState
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypetMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<MypetMainPageState>() {

    private val petResponseFlow: MutableStateFlow<PetResponse?> = MutableStateFlow(null)
    private val petInsuranceFlow: MutableStateFlow<PetInsuranceResponse?> = MutableStateFlow(null)
    private val petIdFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val priceIdFlow: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val companyFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    override val uiState = MypetMainPageState(
        petCount = petResponseFlow.asStateFlow(),
        insuranceSuggestion = petInsuranceFlow.asStateFlow(),
        petId = petIdFlow.asStateFlow(),
        priceId = priceIdFlow.asStateFlow(),
        company = companyFlow.asStateFlow()
    )

    fun onInsuranceNoPetFragmentClick() {
        emitEventFlow(MypetMainEvent.GoToAddPetButtonClick)
    }

    fun onClickContractCheckBtn() {
        emitEventFlow(MypetMainEvent.GoToInsuranceInfoCheckPage)

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