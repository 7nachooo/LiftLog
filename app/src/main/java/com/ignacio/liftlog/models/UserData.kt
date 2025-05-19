package com.ignacio.liftlog.models

data class UserData(
    val name: String,
    val progress: Int, // Porcentaje
    val currentSessions: Int,
    val targetSessions: Int,
    val currentCalories: Int,
    val targetCalories: Int,
    val currentSleep: String,
    val targetSleep: String
)