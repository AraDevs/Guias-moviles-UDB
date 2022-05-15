package com.aradevs.contadorapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.contadorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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