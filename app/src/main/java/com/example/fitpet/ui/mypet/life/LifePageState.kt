package com.example.fitpet.ui.mypet.life

import com.example.fitpet.PageState
import com.example.fitpet.model.domain.AdvertisementItemVo
import com.example.fitpet.model.response.PetLifeHomeResponse
import kotlinx.coroutines.flow.StateFlow

data class LifePageState(
    val currentPage: StateFlow<Int>,
    val pageCount: StateFlow<Int>,
    val advertisement: StateFlow<List<AdvertisementItemVo>>,
    val pageData: StateFlow<PetLifeHomeResponse>
): PageState