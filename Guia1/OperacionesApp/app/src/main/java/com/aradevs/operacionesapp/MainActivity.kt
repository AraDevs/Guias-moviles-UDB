package com.aradevs.operacionesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aradevs.operacionesapp.data.OperationType
import com.aradevs.operacionesapp.databinding.ActivityMainBinding
import com.aradevs.operacionesapp.extensions.parseValueToDouble
import com.aradevs.operacionesapp.view_models.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //setting up view binding in activities, in fragments is a little bit different
        setContentView(binding.root)
        //setting up ui values, in fragments this should be called in the onViewCreated method
        setupUi()
    }

    //in this particular application, this methods establishes the corresponding onclick listener
    private fun setupUi() {
        binding.apply {
            addButton.setOnClickListener {
                operate(OperationType.ADD)
            }
            subtractButton.setOnClickListener {
                operate(OperationType.SUBTRACT)
            }
            multiplyButton.setOnClickListener {
                operate(OperationType.MULTIPLY)
            }
            divideButton.setOnClickListener {
                operate(OperationType.DIVIDE)
            }
        }
    }

    //validates the conditions and calls the operationHandler method from the viewModel
    private fun operate(operationType: OperationType) {
        if (!areFieldsEmpty()) {
            binding.apply {
                if (operationType == OperationType.DIVIDE && secondNumber.parseValueToDouble() == 0.0) {
                    nonApplicableOperation()
                    return
                }
                showResult(
                    viewModel.operationHandler(
                        firstNumber.parseValueToDouble(), //parseValueToDouble() is not an kotlin core function. See more at Extensions.kt
                        secondNumber.parseValueToDouble(),
                        operationType
                    )
                )
            }
        } else {
            showEmptyMessage()
        }
    }

    //region message and result methods
    private fun areFieldsEmpty(): Boolean {
        binding.apply {
            return firstNumber.text.isNullOrEmpty() || secondNumber.text.isNullOrEmpty()
        }
    }

    private fun nonApplicableOperation() {
        Toast.makeText(this, getString(R.string.non_applicable_operation), Toast.LENGTH_SHORT)
            .show()
    }

    private fun showEmptyMessage() {
        Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
    }

    private fun showResult(result: Double?) {
        val parsedResult: String = String.format("%.2f", result)
        Toast.makeText(this, getString(R.string.result, parsedResult), Toast.LENGTH_SHORT).show()
    }
    //endregion
}