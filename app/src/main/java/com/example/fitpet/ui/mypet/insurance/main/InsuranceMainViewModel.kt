package com.example.fitpet.ui.mypet.insurance.main

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.domain.PetType
import com.example.fitpet.model.domain.insurance.main.MyPet
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
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InsuranceMainViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<InsuranceMainPageState>() {
    private val myPetFlow: MutableStateFlow<MyPet> = MutableStateFlow(MyPet(PetType.DOG,"",0,""))
    private val insurancePriceFlow: MutableStateFlow<Int> = MutableStateFlow(10000)
    private val nowMonthFlow: MutableStateFlow<Int> = MutableStateFlow(70)


    override val uiState: InsuranceMainPageState = InsuranceMainPageState(
        petInfo = myPetFlow.asStateFlow(),
        insurancePrice = insurancePriceFlow.asStateFlow(),
        nowMonth = nowMonthFlow.asStateFlow(),
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

    fun updatePrice(input: Int) {
        viewModelScope.launch {
            insurancePriceFlow.update { input }
        }
    }

    fun onClickBtnConsult() {
        emitEventFlow(InsuranceNoRegisterEvent.GoToConsult)
    }

    fun onClickChargeBtn() {
        emitEventFlow(InsuranceNoRegisterEvent.GoToConsult)
    }

    fun onClickCheckBtn() {
        emitEventFlow(InsuranceNoRegisterEvent.GoToConsult)
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
}