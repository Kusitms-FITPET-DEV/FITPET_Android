package com.example.fitpet.ui.mypet.life.hospitalRecord

import com.example.fitpet.PageState
import com.example.fitpet.model.response.HospitalRecordItem
import kotlinx.coroutines.flow.StateFlow

data class HospitalRecordPageState (
    val recordList: StateFlow<List<HospitalRecordItem>>
): PageState