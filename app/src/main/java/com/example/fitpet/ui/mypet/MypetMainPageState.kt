package com.example.fitpet.ui.mypet

import com.example.fitpet.PageState
import com.example.fitpet.model.OnBoardingItemVo
import kotlinx.coroutines.flow.StateFlow

data class MypetMainPageState(
    val temp: String = ""
): PageState