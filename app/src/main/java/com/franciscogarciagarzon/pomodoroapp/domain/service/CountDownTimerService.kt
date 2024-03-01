package com.franciscogarciagarzon.pomodoroapp.domain.service


import com.franciscogarciagarzon.pomodoroapp.data.CountDownTimerContract
import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.CountDownTimerUseCase
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.TimeSlotUseCase
import javax.inject.Inject

class CountDownTimerService
@Inject
constructor(
    private val timeSlotUseCase: TimeSlotUseCase,
    private val timer: CountDownTimerContract

) : CountDownTimerUseCase {
    private lateinit var currentTimeSlot: TimeSlot
    private var currentStatus: Status

    private val onFinishedLambda: () -> Unit = {
        currentStatus = Status.Idle
        val nextTimeSlot = timeSlotUseCase.nextTimeSlot(currentTimeSlot)
        start(nextTimeSlot)
    }


    init {
        currentStatus = Status.Idle
        timer.addOnFinishedLambda(onFinishedLambda)
    }

    override fun currentStatus(): Status {
        return currentStatus
    }

    override fun start(timeSlot: TimeSlot) {
        currentTimeSlot = timeSlot
        timer.start(currentTimeSlot)
        currentStatus = Status.Running(currentTimeSlot)

    }

    override fun reset() {
        currentStatus = Status.Idle
    }
}