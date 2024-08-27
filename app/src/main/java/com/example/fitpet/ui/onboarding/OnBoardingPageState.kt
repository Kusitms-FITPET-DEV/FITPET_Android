package com.example.fitpet.ui.onboarding

import androidx.compose.runtime.mutableStateOf
import com.example.fitpet.PageState
import com.example.fitpet.model.OnBoardingItemVo
import kotlinx.coroutines.flow.StateFlow

data class OnBoardingPageState(
    val onBoardingItemList: StateFlow<List<OnBoardingItemVo>>,
    val isLastPage: StateFlow<Boolean>
): PageState