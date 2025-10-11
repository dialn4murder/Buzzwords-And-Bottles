package com.example.buzzwordsbottles.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()
    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(text: String){
        val currentList = _scannedText.value ?: emptyList()
        _scannedText.value = currentList + Descriptions(text)
    }
}