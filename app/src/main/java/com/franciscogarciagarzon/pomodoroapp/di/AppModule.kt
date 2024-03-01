package com.franciscogarciagarzon.pomodoroapp.di

import com.franciscogarciagarzon.pomodoroapp.data.CountDownTimerContract
import com.franciscogarciagarzon.pomodoroapp.data.SessionManagerContract
import com.franciscogarciagarzon.pomodoroapp.data.device.PomodoroCountDownTimer
import com.franciscogarciagarzon.pomodoroapp.data.device.SessionManager
import com.franciscogarciagarzon.pomodoroapp.domain.service.SessionService
import com.franciscogarciagarzon.pomodoroapp.domain.service.TimeSlotService
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.SessionUseCase
import com.franciscogarciagarzon.pomodoroapp.domain.usecase.TimeSlotUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideCountDownTimer(): CountDownTimerContract {
        return PomodoroCountDownTimer()
    }

    @Provides
    fun provideTimeSlotUseCase(sessionService: SessionUseCase): TimeSlotUseCase {
        return TimeSlotService(sessionService)
    }

    @Provides
    fun provideSessionManager(): SessionManagerContract {
        return SessionManager()
    }


    @Provides
    fun provideSessionUseCase(sessionManager: SessionManagerContract): SessionUseCase {
        return SessionService(sessionManager)
    }

}