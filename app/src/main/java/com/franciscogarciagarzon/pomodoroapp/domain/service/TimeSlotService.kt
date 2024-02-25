package com.franciscogarciagarzon.pomodoroapp.domain.service

import android.os.CountDownTimer
import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.model.durationInMilliseconds
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.TimeSlotUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimeSlotService() : TimeSlotUseCase {
    override suspend fun run(timeSlot: TimeSlot): Flow<Status> {
        var status: Status = Status.Running(timeSlot)

        flow<Status> { emit(status) }
    }
}