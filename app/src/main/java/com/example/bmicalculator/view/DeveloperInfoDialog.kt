package com.example.bmicalculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bmicalculator.databinding.ActivityDeveloperInfoDialogBinding // Import View Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeveloperInfoDialog : BottomSheetDialogFragment() {

    // Deklarasi binding
    private var _binding: ActivityDeveloperInfoDialogBinding? = null
    // Properti untuk mengakses binding dengan aman
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk dialog menggunakan View Binding
        _binding = ActivityDeveloperInfoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Pastikan binding di-null-kan saat view dihancurkan untuk menghindari kebocoran memori
        _binding = null
    }
}
