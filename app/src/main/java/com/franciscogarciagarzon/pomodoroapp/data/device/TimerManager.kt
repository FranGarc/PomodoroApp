package com.franciscogarciagarzon.pomodoroapp.data.device

import android.os.CountDownTimer
import android.util.Log
import com.franciscogarciagarzon.pomodoroapp.data.TimerManagerContract
import com.franciscogarciagarzon.pomodoroapp.data.model.Session
import com.franciscogarciagarzon.pomodoroapp.domain.model.Status
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.model.durationInMilliseconds

object TimerManager : TimerManagerContract {
    override val session: Session = Session()
    override lateinit var currentTimeSlot: TimeSlot
    override var currentStatus: Status = Status.Idle

    override fun runTimeSlot(timeSlot: TimeSlot) {
        Log.d("TimerManager", "runTimeSlot(timeSlot: $timeSlot)")

        currentTimeSlot = timeSlot
        currentStatus = Status.Running(timeSlot)
        object : CountDownTimer(timeSlot.durationInMilliseconds(), 1000L) {
            override fun onTick(p0: Long) {
                Log.d("TimerManager", "onTick(p0: $p0)")
            }

            override fun onFinish() {
                currentStatus = Status.Idle
            }
        }.start()
    }

    override fun stopTimeSlot() {

    }
}