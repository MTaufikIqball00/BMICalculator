package com.example.bmicalculator.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class BmiRepository @Inject constructor() {
    fun calculateBmi(weightKg: Double, heightCm: Double): BmiCalculateResult {
        // Convert height from cm to meters
        val heightM = heightCm / 100.0
        // Calculate BMI
        val bmi = weightKg / heightM.pow(2)

        // Determine BMI category
        val category = when {
            bmi < 18.5 -> "Kurus" // Underweight
            bmi >= 18.5 && bmi < 25.0 -> "Normal" // Normal weight
            bmi >= 25.0 && bmi < 30.0 -> "Gemuk" // Overweight
            else -> "Obesitas" // Obese
        }

        // Return the BMI calculation result
        return BmiCalculateResult(bmi, category)
    }
}