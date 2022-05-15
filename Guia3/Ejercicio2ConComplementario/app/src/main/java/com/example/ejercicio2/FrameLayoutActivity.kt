package com.example.ejercicio2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio2.databinding.ActivityFrameLayoutBinding

class FrameLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrameLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSalir.setOnClickListener {
                finish()
            }
        }
    }
}