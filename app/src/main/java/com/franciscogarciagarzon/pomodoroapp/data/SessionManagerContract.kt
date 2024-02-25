package com.franciscogarciagarzon.pomodoroapp.data

interface SessionManagerContract {
    fun addCompletedPomodoro()
    fun addInterruptedPomodoro()
    fun addShortBreak()
    fun addLongBreak()

    fun numberOfCompletedPomodoro(): Int
    fun numberOfInterruptedPomodoro(): Int
    fun numberOfShortBreak(): Int
    fun numberOfLongBreak(): Int

}