package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityTableLayoutBinding

class TableLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTableLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnSalir.setOnClickListener {
                finish()
            }
        }
    }
}