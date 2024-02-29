package com.franciscogarciagarzon.pomodoroapp.data.device

import com.franciscogarciagarzon.pomodoroapp.data.CountDownTimerContract
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.model.durationInMilliseconds

object PomodoroCountDownTimerObject : PomodoroCountDownTimer()
abstract class PomodoroCountDownTimer : CountDownTimerContract {
    private lateinit var timer: KotlinCountDownTimer
    private lateinit var onFinishedLambda: () -> Unit

    override fun addOnFinishedLambda(onFinishedLambda: () -> Unit) {
        this.onFinishedLambda = onFinishedLambda
    }

    @Synchronized
    override fun start(timeSlot: TimeSlot) {

        val targetTimeInMillis: Long = timeSlot.durationInMilliseconds()
        timer = object : KotlinCountDownTimer(mMillisInFuture = targetTimeInMillis, mCountdownInterval = 1000) {

            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onFinished()
            }
        }.start()

    }

    override fun cancel() {
        timer.cancel()
    }

    override fun onFinished() {
        run { onFinishedLambda() }
    }


}