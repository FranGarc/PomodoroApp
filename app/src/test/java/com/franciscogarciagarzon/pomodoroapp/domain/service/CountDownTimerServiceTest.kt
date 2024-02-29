package com.franciscogarciagarzon.pomodoroapp.domain.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.franciscogarciagarzon.pomodoroapp.data.CountDownTimerContract
import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import org.junit.jupiter.api.Test

object TestTimer : CountDownTimerContract {
    lateinit var onFinishedLambda: () -> Unit

    override fun start(timeSlot: TimeSlot) {
        Unit
    }

    override fun cancel() {
        Unit
    }

    override fun onFinished() {
        { onFinishedLambda }
    }

    override fun addOnFinishedLambda(onFinishedLambda: () -> Unit) {
        this.onFinishedLambda = onFinishedLambda
    }


}

class CountDownTimerServiceTest {

    private val timer: CountDownTimerContract = TestTimer


    @Test
    fun `Initial Status is Idle`() {
        val expectedStatus: Status = Status.Idle
        val cdtService = CountDownTimerService(timer = timer)
        val currentStatus: Status = cdtService.currentStatus()
        assertThat(currentStatus).isEqualTo(expectedStatus)
    }

    @Test
    fun `Start changes status to Running `() {
        val timeSlot = TimeSlot.Pomodoro
        val expectedStatus: Status = Status.Running(timeSlot)
        val cdtService = CountDownTimerService(timer = timer)
        cdtService.start(timeSlot)
        val currentStatus: Status = cdtService.currentStatus()
        assertThat(currentStatus).isEqualTo(expectedStatus)
    }

    @Test
    fun `Reset after Start changes status back to Idle `() {
        val timeSlot = TimeSlot.Pomodoro
        val expectedStatus: Status = Status.Idle
        val cdtService = CountDownTimerService(timer = timer)
        cdtService.start(timeSlot)
        cdtService.reset()
        val currentStatus: Status = cdtService.currentStatus()
        assertThat(currentStatus).isEqualTo(expectedStatus)
    }
}