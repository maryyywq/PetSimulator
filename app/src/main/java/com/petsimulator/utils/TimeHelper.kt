package com.petsimulator.utils

import java.time.LocalTime

//Функция для проверки времени дня
fun isNight(): Boolean {
    val currentHour = LocalTime.now().hour
    return currentHour in 22..23 || currentHour in 0..5
}