package com.example.bmicalculator.view
/*
nama : M Taufik Iqbal
Kelas : P.Andro4
NIM : 10122336
Tanggal Pengerjaan Terakhir : 30-5-2025
 */
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.bmicalculator.R
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.example.bmicalculator.viewmodel.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bmiViewModel: BmiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout menggunakan View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set listener untuk tombol Hitung BMI menggunakan binding
        binding.buttonCalculate.setOnClickListener {
            calculateBmi()
        }

        // Observasi LiveData dari ViewModel untuk hasil BMI
        bmiViewModel.bmiResult.observe(this) { result ->
            // Jika hasil tidak null, navigasi ke BmiResultActivity
            result?.let {
                val intent = Intent(this, BmiResultActivity::class.java).apply {
                    putExtra("BMI_VALUE", it.bmiValue)
                    putExtra("BMI_CATEGORY", it.category)
                }
                startActivity(intent)
            }
        }

        // Observasi LiveData dari ViewModel untuk pesan error
        bmiViewModel.errorMessage.observe(this) { message ->
            // Tampilkan pesan error menggunakan Toast
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk mendapatkan input dan memicu perhitungan BMI di ViewModel
    private fun calculateBmi() {
        // Ambil teks dari EditText menggunakan binding
        val weightStr = binding.editTextWeight.text.toString()
        val heightStr = binding.editTextHeight.text.toString()

        // Panggil fungsi calculateBmi di ViewModel
        bmiViewModel.calculateBmi(weightStr, heightStr)
    }
}