package com.franciscogarciagarzon.pomodoroapp.domain.service

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.franciscogarciagarzon.pomodoroapp.data.device.SessionManager
import com.franciscogarciagarzon.pomodoroapp.data.model.Session
import com.franciscogarciagarzon.pomodoroapp.domain.model.TimeSlot
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.SessionUseCase
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.TimeSlotUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TimeSlotServiceTest {
    lateinit var sessionUseCase: SessionUseCase
    lateinit var timeSlotUsecase: TimeSlotUseCase
    private var initialCompletedPomodoros: Int = -1
    private var initialInterruptedPomodoros: Int = -1
    private var initialLongBreaks: Int = -1
    private var initialShortBreaks: Int = -1

    @BeforeEach
    fun setUp() {
        sessionUseCase = SessionService(sessionManager = SessionManager())
        timeSlotUsecase = TimeSlotService(sessionUseCase)

        initialCompletedPomodoros = sessionUseCase.numberOfCompletedPomodoro()
        initialInterruptedPomodoros = sessionUseCase.numberOfInterruptedPomodoro()
        initialLongBreaks = sessionUseCase.numberOfLongBreak()
        initialShortBreaks = sessionUseCase.numberOfShortBreak()
    }

    @Test
    fun `finish on TimeSlot Pomodoro adds one to Completed Pomodoros but not to the rest of the stats`() {
        val timeSlot = TimeSlot.Pomodoro
        timeSlotUsecase.finish(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros + 1)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks)
        }
    }

    @Test
    fun `finish on TimeSlot Long Break adds one to Completed Long Break but not to the rest of the stats`() {
        val timeSlot = TimeSlot.Break.LongBreak
        timeSlotUsecase.finish(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks + 1)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks)
        }
    }

    @Test
    fun `finish on TimeSlot Short Break adds one to Completed Short Break but not to the rest of the stats`() {
        val timeSlot = TimeSlot.Break.ShortBreak
        timeSlotUsecase.finish(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks + 1)
        }
    }

    @Test
    fun `interrupt on TimeSlot Pomodoro adds one to Interrupted Pomodoro but not to the rest of the stats`() {
        val timeSlot = TimeSlot.Pomodoro
        timeSlotUsecase.interrupt(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros + 1)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks)
        }
    }

    @Test
    fun `interrupt on TimeSlot Short Break changes none of the stats`() {
        val timeSlot = TimeSlot.Break.ShortBreak
        timeSlotUsecase.interrupt(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks)
        }
    }

    @Test
    fun `interrupt on TimeSlot Long Break changes none of the stats`() {
        val timeSlot = TimeSlot.Break.LongBreak
        timeSlotUsecase.interrupt(timeSlot)
        val currentCompletedPomodoros: Int = sessionUseCase.numberOfCompletedPomodoro()
        val currentInterruptedPomodoros: Int = sessionUseCase.numberOfInterruptedPomodoro()
        val currentLongBreaks: Int = sessionUseCase.numberOfLongBreak()
        val currentShortBreaks: Int = sessionUseCase.numberOfShortBreak()
        assertAll {
            assertThat(currentCompletedPomodoros).isEqualTo(initialCompletedPomodoros)
            assertThat(currentInterruptedPomodoros).isEqualTo(initialInterruptedPomodoros)
            assertThat(currentLongBreaks).isEqualTo(initialLongBreaks)
            assertThat(currentShortBreaks).isEqualTo(initialShortBreaks)
        }
    }

    @Test
    fun `when session has N sets of 4 completed pomodoros and less than N Long Breaks, nextTimeSlot returns LongBreak for currentTimeSlot Pomodoro `() {
        val currentTimeSlot = TimeSlot.Pomodoro
        val localSession1 = Session(_numberOfCompletedPomodoros = 4, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 0, _numberOfShortBreaks = 3)
        val localSession2 = Session(_numberOfCompletedPomodoros = 8, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 1, _numberOfShortBreaks = 6)
        val localSession3 = Session(_numberOfCompletedPomodoros = 12, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 2, _numberOfShortBreaks = 9)
        val expectedResult = TimeSlot.Break.LongBreak

        val timeSlotService1 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession1)))
        val timeSlotService2 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession2)))
        val timeSlotService3 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession3)))

        val result1 = timeSlotService1.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result2 = timeSlotService2.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result3 = timeSlotService3.nextTimeSlot(currentTimeSlot = currentTimeSlot)

        assertAll {
            assertThat(result1).isInstanceOf(expectedResult.javaClass)
            assertThat(result2).isInstanceOf(expectedResult.javaClass)
            assertThat(result3).isInstanceOf(expectedResult.javaClass)
        }
    }

    @Test
    fun `when session has N sets of 4 completed pomodoros and  N Long Breaks, nextTimeSlot returns ShortBreak for currentTimeSlot Pomodoro`() {
        val currentTimeSlot = TimeSlot.Pomodoro
        val localSession1 = Session(_numberOfCompletedPomodoros = 4, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 1, _numberOfShortBreaks = 3)
        val localSession2 = Session(_numberOfCompletedPomodoros = 8, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 2, _numberOfShortBreaks = 6)
        val localSession3 = Session(_numberOfCompletedPomodoros = 12, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 3, _numberOfShortBreaks = 9)
        val expectedResult = TimeSlot.Break.ShortBreak

        val timeSlotService1 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession1)))
        val timeSlotService2 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession2)))
        val timeSlotService3 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession3)))

        val result1 = timeSlotService1.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result2 = timeSlotService2.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result3 = timeSlotService3.nextTimeSlot(currentTimeSlot = currentTimeSlot)

        assertAll {
            assertThat(result1).isInstanceOf(expectedResult.javaClass)
            assertThat(result2).isInstanceOf(expectedResult.javaClass)
            assertThat(result3).isInstanceOf(expectedResult.javaClass)
        }
    }

    @Test
    fun `nextTimeSlot returns Pomodoro for currentTimeSlot Short Break, independent of the stats `() {
        val currentTimeSlot = TimeSlot.Break.ShortBreak
        val localSession1 = Session(_numberOfCompletedPomodoros = 3, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 0, _numberOfShortBreaks = 2)
        val localSession2 = Session(_numberOfCompletedPomodoros = 7, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 1, _numberOfShortBreaks = 5)
        val localSession3 = Session(_numberOfCompletedPomodoros = 1, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 2, _numberOfShortBreaks = 8)
        val expectedResult = TimeSlot.Pomodoro

        val timeSlotService1 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession1)))
        val timeSlotService2 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession2)))
        val timeSlotService3 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession3)))

        val result1 = timeSlotService1.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result2 = timeSlotService2.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result3 = timeSlotService3.nextTimeSlot(currentTimeSlot = currentTimeSlot)

        assertAll {
            assertThat(result1).isInstanceOf(expectedResult.javaClass)
            assertThat(result2).isInstanceOf(expectedResult.javaClass)
            assertThat(result3).isInstanceOf(expectedResult.javaClass)
        }
    }

    @Test
    fun `nextTimeSlot returns Pomodoro for currentTimeSlot Long Break, independent of the stats `() {
        val currentTimeSlot = TimeSlot.Break.LongBreak
        val localSession1 = Session(_numberOfCompletedPomodoros = 3, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 0, _numberOfShortBreaks = 2)
        val localSession2 = Session(_numberOfCompletedPomodoros = 7, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 1, _numberOfShortBreaks = 5)
        val localSession3 = Session(_numberOfCompletedPomodoros = 1, _numberOfInterruptedPomodoros = 0, _numberOfLongBreaks = 2, _numberOfShortBreaks = 8)
        val expectedResult = TimeSlot.Pomodoro

        val timeSlotService1 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession1)))
        val timeSlotService2 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession2)))
        val timeSlotService3 = TimeSlotService(sessionService = SessionService(sessionManager = SessionManager(currentSession = localSession3)))

        val result1 = timeSlotService1.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result2 = timeSlotService2.nextTimeSlot(currentTimeSlot = currentTimeSlot)
        val result3 = timeSlotService3.nextTimeSlot(currentTimeSlot = currentTimeSlot)

        assertAll {
            assertThat(result1).isInstanceOf(expectedResult.javaClass)
            assertThat(result2).isInstanceOf(expectedResult.javaClass)
            assertThat(result3).isInstanceOf(expectedResult.javaClass)
        }
    }


}