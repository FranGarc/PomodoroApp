package com.franciscogarciagarzon.pomodoroapp.domain.usecase

interface SessionUseCase {

    fun addCompletedPomodoro()
    fun addInterruptedPomodoro()
    fun addShortBreak()
    fun addLongBreak()

    fun numberOfCompletedPomodoro(): Int
    fun numberOfInterruptedPomodoro(): Int
    fun numberOfShortBreak(): Int
    fun numberOfLongBreak(): Int
}