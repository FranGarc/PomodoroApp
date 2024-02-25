package com.franciscogarciagarzon.pomodoroapp.data.model

data class Session(
    private var _numberOfCompletedPomodoros: Int = 0,
    private var _numberOfInterruptedPomodoros: Int = 0,
    private var _numberOfShortBreaks: Int = 0,
    private var _numberOfLongBreaks: Int = 0,
) {
    val numberOfCompletedPomodoros: Int get() = _numberOfCompletedPomodoros
    val numberOfInterruptedPomodoros: Int get() = _numberOfInterruptedPomodoros
    val numberOfShortBreaks: Int get() = _numberOfShortBreaks
    val numberOfLongBreaks: Int get() = _numberOfLongBreaks

    fun addCompletedPomodoro() {
        _numberOfCompletedPomodoros++
    }

    fun addInterruptedPomodoro() {
        _numberOfInterruptedPomodoros++
    }

    fun addShortBreak() {
        _numberOfShortBreaks++
    }

    fun addLongBreak() {
        _numberOfLongBreaks++
    }
}
