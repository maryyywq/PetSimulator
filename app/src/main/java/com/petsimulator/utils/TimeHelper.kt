package com.petsimulator.utils
import java.time.LocalTime

fun isPetSleeping(): Boolean {
    val currentTime = LocalTime.now()
    return currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.of(6, 0))
}