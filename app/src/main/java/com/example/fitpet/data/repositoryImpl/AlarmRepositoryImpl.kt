package com.example.fitpet.data.repositoryImpl

import com.example.fitpet.data.repository.AlarmRepository
import com.example.fitpet.data.service.AlarmService
import com.example.fitpet.model.response.AlarmHistoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmService: AlarmService
): AlarmRepository {

    override suspend fun getAlarmHistoryList(): Flow<Result<List<AlarmHistoryResponse.HistoryItem>>> = flow {
        emit(
            runCatching {
                val response = alarmService.getAlarmList()

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Get Alarm History List Failed -> ${response.status}")
                }
            }.onFailure {
                Timber.d("[서버통신 테스트] -> repository error ${it.message} ")
            }
        )
    }

    override suspend fun changeAlarmConfirm(historyId: Int): Flow<Result<Unit>> = flow {
        emit(
            runCatching {
                val response = alarmService.changeAlarmConfirm(historyId)

                if (response.success) {
                    response.data
                } else {
                    throw RuntimeException("Get Alarm History List Failed -> ${response.status}")
                }
            }
        )
    }
}