package com.franciscogarciagarzon.pomodoroapp.data

import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot

interface CountDownTimerContract {

    fun start(timeSlot: TimeSlot)
    fun cancel()
    fun onFinished()

    fun addOnFinishedLambda(onFinishedLambda: () -> Unit)
}