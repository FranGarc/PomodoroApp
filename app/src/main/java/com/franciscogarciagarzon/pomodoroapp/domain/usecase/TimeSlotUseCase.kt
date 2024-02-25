package com.franciscogarciagarzon.pomodoroapp.domain.usecase

import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot

interface TimeSlotUseCase {
    fun finish(timeSlot: TimeSlot)// Flow<Status>

    fun interrupt(timeSlot: TimeSlot) //: Flow<Status>

    fun nextTimeSlot(currentTimeSlot: TimeSlot): TimeSlot
}