package com.example.fitpet.ui.mypet.life

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.model.domain.AdvertisementItemVo
import com.example.fitpet.model.response.PetLifeHomeResponse
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LifeViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val petLifeRepository: PetLifeRepository
): BaseViewModel<LifePageState>() {
    private val currentPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    private val pageCountStateFlow: MutableStateFlow<Int> = MutableStateFlow(3)
    private val pageDataStateFlow: MutableStateFlow<PetLifeHomeResponse> = MutableStateFlow(PetLifeHomeResponse())
    private val advertisementStateFlow: MutableStateFlow<List<AdvertisementItemVo>> = MutableStateFlow(emptyList())

    init {
        getPetLifeHomeData()
    }

    override val uiState = LifePageState(
        currentPage = currentPageStateFlow.asStateFlow(),
        pageCount = pageCountStateFlow.asStateFlow(),
        advertisement = advertisementStateFlow.asStateFlow(),
        pageData = pageDataStateFlow.asStateFlow()
    )

    fun onClickGetNewQuote() {
        emitEventFlow(LifeEvent.GetNewQuote)
    }

    fun updatePageCount(position: Int) {
        currentPageStateFlow.update { position }
    }

    private fun getPetLifeHomeData() {
        viewModelScope.launch {
            petLifeRepository.getPetLifeHome().collect {
                resultResponse(it, ::onSuccessGetPetLifeHomeData)
            }
        }
    }

    private fun onSuccessGetPetLifeHomeData(data: PetLifeHomeResponse) {
        viewModelScope.launch {
            pageDataStateFlow.update { data }
            advertisementStateFlow.update {
                listOf(
                    AdvertisementItemVo(1, R.drawable.ic_advertisement1),
                    AdvertisementItemVo(2, R.drawable.ic_advertisement2),
                    AdvertisementItemVo(3, R.drawable.ic_advertisement3)
                )
            }
        }
    }

    fun onClickBtnTrue() {
        if (uiState.pageData.value.quiz.answer) emitEventFlow(LifeEvent.ShowCorrectDialog)
        else emitEventFlow(LifeEvent.ShowFalseDialog)
    }

    fun onClickBtnFalse() {
        if (uiState.pageData.value.quiz.answer) emitEventFlow(LifeEvent.ShowFalseDialog)
        else emitEventFlow(LifeEvent.ShowCorrectDialog)
    }

    fun onClickHospitalRecordDetail() {
        emitEventFlow(LifeEvent.GoToHospitalRecordDetail)
    }
}