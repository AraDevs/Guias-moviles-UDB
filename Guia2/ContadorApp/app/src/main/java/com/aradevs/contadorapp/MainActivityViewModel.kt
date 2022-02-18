package com.aradevs.contadorapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val counterValue: MutableLiveData<Int> = MutableLiveData(0)
    fun addToCount(){
        when(counterValue.value){
            in 0..8 -> counterValue.postValue(counterValue.value?.plus(1))
            else-> counterValue.postValue(0)
        }
    }
}