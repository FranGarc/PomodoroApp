package com.franciscogarciagarzon.pomodoroapp.data.device

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.franciscogarciagarzon.pomodoroapp.data.SessionManagerContract
import org.junit.jupiter.api.Test

class SessionManagerTest {
    private val sessionManager: SessionManagerContract = SessionManager()

    @Test
    fun `addCompletedPomodoro adds exactly one to CompletedPomodoro`() {
        val currentCompletedPomodoros = sessionManager.numberOfCompletedPomodoro()
        val expectedResult = currentCompletedPomodoros + 1
        sessionManager.addCompletedPomodoro()
        val result = sessionManager.numberOfCompletedPomodoro()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addInterruptedPomodoro adds exactly one to InterruptedPomodoro`() {
        val currentInterruptedPomodoro = sessionManager.numberOfInterruptedPomodoro()
        val expectedResult = currentInterruptedPomodoro + 1
        sessionManager.addInterruptedPomodoro()
        val result = sessionManager.numberOfInterruptedPomodoro()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addShortBreak adds exactly one to ShortBreak`() {
        val currentShortBreaks = sessionManager.numberOfShortBreak()
        val expectedResult = currentShortBreaks + 1
        sessionManager.addShortBreak()
        val result = sessionManager.numberOfShortBreak()
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `addLongBreak adds exactly one to LongBreak`() {
        val currentLongBreaks = sessionManager.numberOfLongBreak()
        val expectedResult = currentLongBreaks + 1
        sessionManager.addLongBreak()
        val result = sessionManager.numberOfLongBreak()
        assertThat(result).isEqualTo(expectedResult)
    }
}