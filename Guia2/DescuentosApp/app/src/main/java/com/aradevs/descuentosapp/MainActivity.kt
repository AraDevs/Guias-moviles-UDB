package com.aradevs.descuentosapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aradevs.descuentosapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var data: Array<Double?> = arrayOfNulls(5)
    private val hourRate: Double = 8.5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            calculateButton.setOnClickListener {
                calculate()
            }
        }
    }

    private fun areFieldsEmpty(): Boolean {
        return binding.name.text.isNullOrEmpty() || binding.hours.text.isNullOrEmpty()
    }

    private fun calculate() {
        if (!areFieldsEmpty()) {
            var hours: Double? = null
            try {
                hours = binding.hours.text.toString().toDouble()
                data.apply {
                    val total = hours * hourRate
                    var subtotal: Double? = total
                    this[0] = (total * 0.02)
                    this[1] = (total * 0.03)
                    this[2] = (total * 0.04)
                    this.forEachIndexed { index, value ->
                        if(index in 0..2){
                            subtotal = subtotal?.minus(value ?: 0.0)
                        }
                    }
                    this[3] = total
                    this[4] = subtotal
                }
                openActivity()
            } catch (e: NumberFormatException) {
                Snackbar.make(binding.hours, R.string.invalid_int, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun openActivity() {
        val intent = Intent(this, ResultsActivity::class.java)
        val bundle = Bundle()
        bundle.apply {
            this.putString("name", binding.name.text.toString())
            this.putStringArray("data", data.map { it.toString() }.toTypedArray())
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}