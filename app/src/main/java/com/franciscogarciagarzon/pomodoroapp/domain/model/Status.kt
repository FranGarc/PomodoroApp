package com.franciscogarciagarzon.pomodoroapp.domain.model

sealed class Status {
    data object Idle : Status()
    data class Running(val timeSlot: TimeSlot, val elapsedTime: Long = 0) : Status()
}