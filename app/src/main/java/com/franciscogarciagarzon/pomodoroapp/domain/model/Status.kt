package com.franciscogarciagarzon.pomodoroapp.domain.model

sealed class Status {
    object Idle : Status()
    data class Running(val timeSlot: TimeSlot) : Status()
}