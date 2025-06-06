package com.example.bmicalculator.view
/*
nama : M Taufik Iqbal
Kelas : P.Andro4
NIM : 10122336
Tanggal Pengerjaan Terakhir : 30-5-2025
 */
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.R
import com.example.bmicalculator.databinding.ActivityBmiResultBinding

class BmiResultActivity : AppCompatActivity() {

    // Declare binding
    private lateinit var binding: ActivityBmiResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout using View Binding
        binding = ActivityBmiResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from Intent
        val bmiValue = intent.getDoubleExtra("BMI_VALUE", 0.0)
        val bmiCategory = intent.getStringExtra("BMI_CATEGORY")

        // Display BMI result using binding
        binding.tvBmiValue.text = String.format("Nilai BMI Anda : %.2f", bmiValue)
        binding.tvBmiCategory.text = "Kategori BMI : $bmiCategory"

        // Set listener for the Developer Info button using binding
        binding.btnInfoPengembang.setOnClickListener {
            showDeveloperInfoDialog()
        }
    }

    // Function to display the developer info dialog
    private fun showDeveloperInfoDialog() {
        val dialog = DeveloperInfoDialog()
        dialog.show(supportFragmentManager, "DeveloperInfoDialog")
    }
}
