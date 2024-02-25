package com.franciscogarciagarzon.pomodoroapp.domain.model

sealed class TimeSlot(val durationInMinutes: Long) {
    object Pomodoro : TimeSlot(25)
    sealed class Break(durationInMinutes: Long) : TimeSlot(durationInMinutes) {
        object ShortBreak : Break(5)
        object LongBreak : Break(20)
    }
}

fun TimeSlot.durationInMinutes(): Long {
    return this.durationInMinutes
}

fun TimeSlot.durationInSeconds(): Long {
    return this.durationInMinutes * 60
}

fun TimeSlot.durationInMilliseconds(): Long {
    return this.durationInSeconds() * 1000
}