package com.example.translator_2.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}