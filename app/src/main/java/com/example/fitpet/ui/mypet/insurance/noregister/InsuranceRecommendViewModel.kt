package com.example.fitpet.ui.mypet.insurance.noregister

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetsRepository
import com.example.fitpet.model.response.GetPetInsuranceResponse
import com.example.fitpet.model.response.PetInsuranceResponse
import com.example.fitpet.ui.mypet.insurance.main.InsuranceMainEvent
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
class InsuranceRecommendViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petsRepository: PetsRepository
): BaseViewModel<InsuranceRecommendPageState>() {
    private val petInsuranceFlow: MutableStateFlow<GetPetInsuranceResponse?> = MutableStateFlow(null)
    private val isOpenedFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)

    override val uiState: InsuranceRecommendPageState = InsuranceRecommendPageState(
        insuranceSuggestionResponse = petInsuranceFlow.asStateFlow(),
        isOpened = isOpenedFlow.asStateFlow()
    )

    fun loadInsuranceData(petId: Int, priceId: Int) {
        viewModelScope.launch {
            petsRepository.getPetInsuranceInfo(petId,priceId).collect { result ->
                result.onSuccess { response ->
                    // Update both pet info and estimate list
                    petInsuranceFlow.update { response }
                    emitEventFlow(InsuranceRecommendEvent.FetchInsurance)
                }.onFailure {
                    // Handle error
                    Timber.e(it)
                }
            }
        }
    }

    fun onClickBtnConsult() {
        emitEventFlow(InsuranceRecommendEvent.GoToConsult)
    }

    fun updateDetailPopup(){
        Timber.d(" 테스트 ${isOpenedFlow.value}")
        isOpenedFlow.update { !isOpenedFlow.value }
    }

    fun getFormattedPrice(price: Int): String? {
        Timber.d(NumberFormat.getNumberInstance(Locale.US).format(price))
        return if (price == 0) {
            ""
        } else {
            NumberFormat.getNumberInstance(Locale.US).format(price)
        }
    }
}