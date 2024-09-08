package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.AlarmHistoryResponse
import retrofit2.http.GET

interface AlarmService {

    @GET(Endpoints.Alarm.HISTORY)
    suspend fun getAlarmList(): ApiResponse<List<AlarmHistoryResponse.HistoryItem>>

}