package com.example.bmicalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.repository.BmiCalculateResult
import com.example.bmicalculator.repository.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// Anotasi @HiltViewModel untuk injeksi ViewModel
@HiltViewModel
class BmiViewModel @Inject constructor(
    private val bmiRepository: BmiRepository
) : ViewModel() {

    // LiveData untuk menyimpan hasil BMI
    private val _bmiResult = MutableLiveData<BmiCalculateResult?>()
    val bmiResult: LiveData<BmiCalculateResult?> = _bmiResult

    // LiveData untuk menyimpan pesan error
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    // Fungsi untuk menghitung BMI
    fun calculateBmi(weightStr: String, heightStr: String) {
        // Reset pesan error
        _errorMessage.value = null

        // Validasi input
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            _errorMessage.value = "Berat badan dan tinggi badan tidak boleh kosong."
            _bmiResult.value = null // Pastikan hasil BMI juga direset
            return
        }

        try {
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()

            if (weight <= 0 || height <= 0) {
                _errorMessage.value = "Berat badan dan tinggi badan harus lebih dari nol."
                _bmiResult.value = null
                return
            }

            // Jalankan perhitungan di Coroutine untuk menghindari blocking UI thread
            viewModelScope.launch(Dispatchers.Default) {
                val result = bmiRepository.calculateBmi(weight, height)
                _bmiResult.postValue(result) // Update LiveData di main thread
            }
        } catch (e: NumberFormatException) {
            _errorMessage.value = "Input tidak valid. Harap masukkan angka."
            _bmiResult.value = null
        }
    }
}
