package com.franciscogarciagarzon.pomodoroapp.domain.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.franciscogarciagarzon.pomodoroapp.data.SessionManagerContract
import com.franciscogarciagarzon.pomodoroapp.data.device.SessionManager
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.SessionUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SessionServiceTest {
    private val sessionManager: SessionManagerContract = SessionManager()
    private val sessionUseCase: SessionUseCase = SessionService(sessionManager)

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `numberOfCompletedPomodoro returns sessionManager's number of completed pomodoros`() {
        val sessionManagerCompletedPomodoro = sessionManager.numberOfCompletedPomodoro()
        val sessionServiceCompletedPomodoro = sessionUseCase.numberOfCompletedPomodoro()
        assertThat(sessionManagerCompletedPomodoro).isEqualTo(sessionServiceCompletedPomodoro)
    }

    @Test
    fun `numberOfInterruptedPomodoro returns sessionManager's number of interrupted pomodoros`() {
        val sessionManagerInterruptedPomodoro = sessionManager.numberOfInterruptedPomodoro()
        val sessionServiceInterruptedPomodoro = sessionUseCase.numberOfInterruptedPomodoro()
        assertThat(sessionManagerInterruptedPomodoro).isEqualTo(sessionServiceInterruptedPomodoro)
    }

    @Test
    fun `numberOfShortBreaks returns sessionManager's number of short breaks`() {
        val sessionManagerShortBreaks = sessionManager.numberOfShortBreak()
        val sessionServiceShortBreaks = sessionUseCase.numberOfShortBreak()
        assertThat(sessionManagerShortBreaks).isEqualTo(sessionServiceShortBreaks)
    }

    @Test
    fun `numberOfLongBreaks returns sessionManager's number of long breaks`() {
        val sessionManagerLongBreaks = sessionManager.numberOfLongBreak()
        val sessionServiceLongBreaks = sessionUseCase.numberOfLongBreak()
        assertThat(sessionManagerLongBreaks).isEqualTo(sessionServiceLongBreaks)
    }


    @Test
    fun `addCompletedPomodoro adds exactly one to CompletedPomodoro`() {
        val currentCompletedPomodoros = sessionUseCase.numberOfCompletedPomodoro()
        val expectedResult = currentCompletedPomodoros + 1
        sessionUseCase.addCompletedPomodoro()
        val result = sessionUseCase.numberOfCompletedPomodoro()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addInterruptedPomodoro adds exactly one to InterruptedPomodoro`() {
        val currentInterruptedPomodoro = sessionUseCase.numberOfInterruptedPomodoro()
        val expectedResult = currentInterruptedPomodoro + 1
        sessionUseCase.addInterruptedPomodoro()
        val result = sessionUseCase.numberOfInterruptedPomodoro()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addShortBreak adds exactly one to ShortBreak`() {
        val currentShortBreaks = sessionUseCase.numberOfShortBreak()
        val expectedResult = currentShortBreaks + 1
        sessionUseCase.addShortBreak()
        val result = sessionUseCase.numberOfShortBreak()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addLongBreak adds exactly one to LongBreak`() {
        val currentLongBreaks = sessionUseCase.numberOfLongBreak()
        val expectedResult = currentLongBreaks + 1
        sessionUseCase.addLongBreak()
        val result = sessionUseCase.numberOfLongBreak()
        assertThat(result).isEqualTo(expectedResult)
    }
}