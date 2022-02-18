package com.aradevs.contadorapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aradevs.contadorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        liveDataObserver()
        setupUi()
    }

    private fun setupUi() {
        binding.counterButton.setOnClickListener {
            viewModel.addToCount()
        }
    }

    private fun liveDataObserver() {
        viewModel.counterValue.observe(this) {
            binding.counter.text = it.toString()
        }
    }
}