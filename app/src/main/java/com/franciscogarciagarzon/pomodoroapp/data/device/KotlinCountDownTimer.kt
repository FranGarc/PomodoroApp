package com.franciscogarciagarzon.pomodoroapp.data.device

import android.os.Handler
import android.os.Message
import android.os.SystemClock

abstract class KotlinCountDownTimer(
    /**
     * Millis since epoch when alarm should stop.
     */
    private val mMillisInFuture: Long,
    /**
     * The interval in millis that the user receives callbacks
     */
    private var mCountdownInterval: Long
) {
    private var mStopTimeInFuture: Long = 0

    /**
     * boolean representing if the timer was cancelled
     */
    private var mCancelled = false

    /**
     * Cancel the countdown.
     */
    @Synchronized
    fun cancel() {
        mCancelled = true
        mHandler.removeMessages(MSG)
    }

    /**
     * Start the countdown.
     */
    @Synchronized
    fun start(): KotlinCountDownTimer {
        mCancelled = false
        if (mMillisInFuture <= 0) {
            onFinish()
            return this
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture
        mHandler.sendMessage(mHandler.obtainMessage(MSG))
        return this
    }

    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    abstract fun onTick(millisUntilFinished: Long)

    /**
     * Callback fired when the time is up.
     */
    abstract fun onFinish()

    // handles counting down
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            synchronized(this@KotlinCountDownTimer) {
                if (mCancelled) {
                    return
                }
                val millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime()
                if (millisLeft <= 0) {
                    onFinish()
                } else {
                    val lastTickStart = SystemClock.elapsedRealtime()
                    onTick(millisLeft)

                    // take into account user's onTick taking time to execute
                    val lastTickDuration = SystemClock.elapsedRealtime() - lastTickStart
                    var delay: Long
                    if (millisLeft < mCountdownInterval) {
                        // just delay until done
                        delay = millisLeft - lastTickDuration

                        // special case: user's onTick took more than interval to
                        // complete, trigger onFinish without delay
                        if (delay < 0) delay = 0
                    } else {
                        delay = mCountdownInterval - lastTickDuration

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0) delay += mCountdownInterval
                    }
                    sendMessageDelayed(obtainMessage(MSG), delay)
                }
            }
        }
    }

    /**
     * @param millisInFuture The number of millis in the future from the call
     * to [.start] until the countdown is done and [.onFinish]
     * is called.
     * @param countDownInterval The interval along the way to receive
     * [.onTick] callbacks.
     */
    init {
        mCountdownInterval = mCountdownInterval
    }

    companion object {
        private const val MSG = 1
    }
}

