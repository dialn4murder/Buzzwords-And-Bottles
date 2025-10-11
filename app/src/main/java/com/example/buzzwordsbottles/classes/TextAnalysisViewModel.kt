package com.example.buzzwordsbottles.classes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextAnalysisViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()
    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(text: String){
        val currentList = _scannedText.value ?: emptyList()
        _scannedText.value = currentList + Descriptions(text)

        currentList.forEach { i ->
            Log.d("AAAAAA", i.description)
        }

    }
}