package com.example.buzzwordsbottles.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var scannedText = mutableListOf<Descriptions>()

    fun setScannedText(text: String){
        scannedText.add(Descriptions(text))
    }
}