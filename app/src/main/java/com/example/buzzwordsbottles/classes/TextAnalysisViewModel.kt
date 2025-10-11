package com.example.buzzwordsbottles.classes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextAnalysisViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()
    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(text: String){
        // Null safety if the initial scanned text is empty
        val currentList = _scannedText.value ?: emptyList()
        // Adds the scanned text to the lazy list
        _scannedText.value = currentList + Descriptions(text)

    }
}