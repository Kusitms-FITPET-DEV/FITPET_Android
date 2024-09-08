package com.example.fitpet.data.service

import com.example.fitpet.base.ApiResponse
import com.example.fitpet.model.response.AlarmHistoryResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlarmService {

    @GET(Endpoints.Alarm.HISTORY)
    suspend fun getAlarmList(): ApiResponse<List<AlarmHistoryResponse.HistoryItem>>

    @POST("${Endpoints.Alarm.HISTORY}/{historyId}")
    suspend fun changeAlarmConfirm(
        @Path("historyId") historyId: Int
    ): ApiResponse<Unit>
}