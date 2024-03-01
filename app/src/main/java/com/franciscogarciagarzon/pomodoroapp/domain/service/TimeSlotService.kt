package com.franciscogarciagarzon.pomodoroapp.domain.service

import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.model.WrongTimeSlotException
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.SessionUseCase
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.TimeSlotUseCase
import javax.inject.Inject

class TimeSlotService
@Inject constructor(
    private val sessionService: SessionUseCase
) : TimeSlotUseCase {

    override fun finish(timeSlot: TimeSlot) {
        addCurrentTimeSlotToSession(timeSlot)
    }

    override fun interrupt(timeSlot: TimeSlot) {
        if (timeSlot is TimeSlot.Pomodoro) sessionService.addInterruptedPomodoro()
    }

    override fun nextTimeSlot(currentTimeSlot: TimeSlot): TimeSlot {
        val numberOfCompletedPomodoros = sessionService.numberOfCompletedPomodoro()
        val numberOfTakenLongBreaks = sessionService.numberOfLongBreak()

        return when (currentTimeSlot) {
            is TimeSlot.Break -> TimeSlot.Pomodoro
            is TimeSlot.Pomodoro -> calculateBreak(numberOfCompletedPomodoros, numberOfTakenLongBreaks)
            else -> throw WrongTimeSlotException("[${currentTimeSlot}] isn't one of the accepted TimeSlot subclasses ")
        }
    }

    private fun addCurrentTimeSlotToSession(currentTimeSlot: TimeSlot) {
        when (currentTimeSlot) {
            is TimeSlot.Break.LongBreak -> sessionService.addLongBreak()
            is TimeSlot.Break.ShortBreak -> sessionService.addShortBreak()
            is TimeSlot.Pomodoro -> sessionService.addCompletedPomodoro()
        }
    }

    private fun calculateBreak(numberOfCompletedPomodoros: Int, numberOfTakenLongBreaks: Int): TimeSlot.Break {
        val howManyLongBreaksMatchTheNumberOfCompletedPomodoros: Int = numberOfCompletedPomodoros / 4
        val longBreakIsDue = howManyLongBreaksMatchTheNumberOfCompletedPomodoros > numberOfTakenLongBreaks
        val enoughPomodorosHaveBeenCompleted = numberOfCompletedPomodoros % 4 == 0
        val breakType = if (longBreakIsDue && enoughPomodorosHaveBeenCompleted) {
            TimeSlot.Break.LongBreak
        } else {
            TimeSlot.Break.ShortBreak
        }
        return breakType
    }


}

