package com.example.fitpet.ui.mypet.life.faq

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.FaqType
import com.example.fitpet.model.response.FaqItemVo
import kotlinx.coroutines.flow.StateFlow

data class FaqPageState(
    val faqList: StateFlow<List<FaqItemVo>>,
    val selectedFaqType: StateFlow<FaqType>
): PageState
