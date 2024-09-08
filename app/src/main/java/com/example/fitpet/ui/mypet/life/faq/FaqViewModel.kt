package com.example.fitpet.ui.mypet.life.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(
    private val petLifeRepository: PetLifeRepository
): BaseViewModel<FaqPageState>() {
    private val faqListStateFlow: MutableStateFlow<List<FaqItemVo>> = MutableStateFlow(emptyList())
    private val selectedFaqTypeStateFlow: MutableStateFlow<FaqType> = MutableStateFlow(FaqType.REWARD)

    override val uiState: FaqPageState = FaqPageState(
        faqList = faqListStateFlow.asStateFlow(),
        selectedFaqType = selectedFaqTypeStateFlow.asStateFlow()
    )

    init {
        getFaqList(FaqType.REWARD)
    }

    fun getFaqList(keyword: FaqType) {
        viewModelScope.launch {
            selectedFaqTypeStateFlow.update { keyword }
            petLifeRepository.getFaqList(keyword).collect {
                resultResponse(it, ::onSuccessGetFaqList)
            }
        }
    }

    private fun onSuccessGetFaqList(list: List<FaqItemVo>) {
        viewModelScope.launch {
            faqListStateFlow.update { list }
        }
    }
}