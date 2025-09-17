package com.example.buzzwordsbottles.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<String>()
    val scannedText: LiveData<String> = _scannedText

    fun setScannedText(text: String) {
        _scannedText.value = text
    }
}