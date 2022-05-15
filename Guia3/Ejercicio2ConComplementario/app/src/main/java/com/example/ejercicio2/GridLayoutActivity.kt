package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnSalir.setOnClickListener {
                finish()
            }
        }
    }
}