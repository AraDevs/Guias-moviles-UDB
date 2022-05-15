package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityLinearLayoutBinding

class LinearLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnSalir.setOnClickListener {
                finish()
            }
        }
    }
}