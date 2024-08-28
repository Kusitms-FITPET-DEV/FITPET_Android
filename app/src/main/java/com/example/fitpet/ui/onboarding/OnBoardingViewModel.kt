package com.example.fitpet.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.OnBoardingItemVo
import com.example.fitpet.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
): BaseViewModel<OnBoardingPageState>() {
    private val onBoardingItemList: MutableStateFlow<List<OnBoardingItemVo>> = MutableStateFlow(emptyList())
    private val isLastPage: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val uiState = OnBoardingPageState(
        onBoardingItemList = onBoardingItemList.asStateFlow(),
        isLastPage = isLastPage.asStateFlow()
    )

    fun initOnBoardingItem() {
        viewModelScope.launch {
            onBoardingItemList.update {
                listOf(
                    OnBoardingItemVo(
                        title = resourceProvider.getString(R.string.onboarding1_title),
                        content = resourceProvider.getString(R.string.onboarding1_content),
                        img = R.drawable.img_onboarding
                    ),
                    OnBoardingItemVo(
                        title = resourceProvider.getString(R.string.onboarding2_title),
                        content = resourceProvider.getString(R.string.onboarding2_content),
                        img = R.drawable.img_onboarding
                    ),
                    OnBoardingItemVo(
                        title = resourceProvider.getString(R.string.onboarding3_title),
                        content = resourceProvider.getString(R.string.onboarding3_content),
                        img = R.drawable.img_onboarding
                    )
                )
            }
        }
    }

    fun setIsLastPage(value: Boolean) {
        viewModelScope.launch {
            isLastPage.update { value }
        }
    }

    fun onClickBtnStart() {
        emitEventFlow(OnBoardingEvent.GoToKakaoLogin)
    }
}