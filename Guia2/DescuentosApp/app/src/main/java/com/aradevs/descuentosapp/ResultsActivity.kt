package com.aradevs.descuentosapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aradevs.descuentosapp.databinding.ActivityResultsBinding
import java.text.DecimalFormat

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var data: Array<String> = intent.getStringArrayExtra("data") ?: emptyArray()
        binding.name.text = intent.getStringExtra("name")
        data = data.map { DecimalFormat("#.##").format(it.toDouble()) }.toTypedArray()

        with(data) {
            binding.isss.text = getString(R.string.isss_result, this[0])
            binding.afp.text = getString(R.string.afp_result, this[1])
            binding.rent.text = getString(R.string.rent_result, this[2])
            binding.baseSalary.text = getString(R.string.salary_result, this[3])
            binding.perceivedSalary.text = getString(R.string.perceived_salary_result, this[4])
        }
    }
}