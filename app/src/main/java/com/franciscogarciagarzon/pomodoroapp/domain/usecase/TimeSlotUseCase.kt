package com.franciscogarciagarzon.pomodoroapp.domain.usecase

import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import kotlinx.coroutines.flow.Flow

interface TimeSlotUseCase {
    suspend fun run(timeSlot: TimeSlot): Flow<Status>
    suspend fun stop(): Flow<Status>
}