package com.aradevs.operacionesapp.view_models

import androidx.lifecycle.ViewModel
import com.aradevs.operacionesapp.data.OperationType

class MainActivityViewModel : ViewModel() {
    //returns the corresponding operation result
    fun operationHandler(firstNumber: Double, secondNumber: Double, operator: OperationType): Double {
        return when(operator){
            OperationType.ADD -> firstNumber + secondNumber
            OperationType.SUBTRACT -> firstNumber - secondNumber
            OperationType.MULTIPLY -> firstNumber * secondNumber
            OperationType.DIVIDE ->firstNumber / secondNumber
        }
    }
}