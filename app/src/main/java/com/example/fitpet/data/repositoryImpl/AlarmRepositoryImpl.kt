package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.AlarmRepository
import com.example.fitpet.data.service.AlarmService
import com.example.fitpet.model.response.AlarmHistoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmService: AlarmService
): AlarmRepository {

    override suspend fun getAlarmHistoryList(): Flow<Result<AlarmHistoryResponse>> = flow {
        emit(
            runCatching {
                val response = alarmService.getAlarmList()

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Get Alarm History List Failed -> ${response.status}")
                }
            }
        )
    }
}