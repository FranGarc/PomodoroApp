package com.franciscogarciagarzon.pomodoroapp.data

import com.franciscogarciagarzon.pomodoroapp.data.model.Session
import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot

interface TimerManagerContract {
    val session: Session
    var currentTimeSlot: TimeSlot
    var currentStatus: Status
    fun runTimeSlot(timeSlot: TimeSlot)
    fun stopTimeSlot()
}