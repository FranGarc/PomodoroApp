package com.franciscogarciagarzon.pomodoroapp.domain.service

import com.franciscogarciagarzon.pomodoroapp.data.SessionManagerContract
import com.franciscogarciagarzon.pomodoroapp.data.device.SessionManager
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.SessionUseCase

class SessionService(private val sessionManager: SessionManagerContract = SessionManager) : SessionUseCase {
    override fun addCompletedPomodoro() {
        sessionManager.addCompletedPomodoro()
    }

    override fun addInterruptedPomodoro() {
        sessionManager.addInterruptedPomodoro()
    }

    override fun addShortBreak() {
        sessionManager.addShortBreak()
    }

    override fun addLongBreak() {
        sessionManager.addLongBreak()
    }

    override fun numberOfCompletedPomodoro(): Int {
        return sessionManager.numberOfCompletedPomodoro()
    }

    override fun numberOfInterruptedPomodoro(): Int {
        return sessionManager.numberOfInterruptedPomodoro()
    }

    override fun numberOfShortBreak(): Int {
        return sessionManager.numberOfShortBreak()
    }

    override fun numberOfLongBreak(): Int {
        return sessionManager.numberOfLongBreak()
    }
}