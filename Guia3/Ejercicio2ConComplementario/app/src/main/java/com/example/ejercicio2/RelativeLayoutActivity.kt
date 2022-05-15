package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityRelativeLayoutBinding

class RelativeLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRelativeLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelativeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnSalir.setOnClickListener {
                finish()
            }
        }
    }
}