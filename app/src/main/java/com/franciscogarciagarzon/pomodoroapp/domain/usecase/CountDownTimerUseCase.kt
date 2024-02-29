package com.franciscogarciagarzon.pomodoroapp.domain.usecase

import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot

interface CountDownTimerUseCase {
    fun currentStatus(): Status
    fun start(timeSlot: TimeSlot)
    fun reset()
}