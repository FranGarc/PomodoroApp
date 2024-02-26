package com.franciscogarciagarzon.pomodoroapp.data.device

import com.franciscogarciagarzon.pomodoroapp.data.SessionManagerContract
import com.franciscogarciagarzon.pomodoroapp.data.model.Session

class SessionManager(private val currentSession: Session = Session()) : SessionManagerContract {

    override fun addCompletedPomodoro() {
        currentSession.addCompletedPomodoro()
    }

    override fun addInterruptedPomodoro() {
        currentSession.addInterruptedPomodoro()
    }

    override fun addShortBreak() {
        currentSession.addShortBreak()
    }

    override fun addLongBreak() {
        currentSession.addLongBreak()
    }

    override fun numberOfCompletedPomodoro(): Int {
        return currentSession.numberOfCompletedPomodoros
    }

    override fun numberOfInterruptedPomodoro(): Int {
        return currentSession.numberOfInterruptedPomodoros
    }

    override fun numberOfShortBreak(): Int {
        return currentSession.numberOfShortBreaks
    }

    override fun numberOfLongBreak(): Int {
        return currentSession.numberOfLongBreaks
    }
}