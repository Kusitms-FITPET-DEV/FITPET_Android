package com.example.fitpet.data.repository

import com.example.fitpet.model.response.AlarmHistoryResponse
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    suspend fun getAlarmHistoryList(): Flow<Result<List<AlarmHistoryResponse.HistoryItem>>>

    suspend fun changeAlarmConfirm(historyId: Int): Flow<Result<Unit>>
}