package com.example.buzzwordsbottles.classes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buzzwordsbottles.SnackbarAction
import com.example.buzzwordsbottles.SnackbarController
import com.example.buzzwordsbottles.SnackbarEvent
import kotlinx.coroutines.launch

class TextAnalysisViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()

    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(text: String){
        // Null safety if the initial scanned text is empty
        val currentList = _scannedText.value ?: emptyList()
        // Adds the scanned text to the lazy list
        _scannedText.value = currentList + Descriptions(text)
        showSnackbar()

    }

    fun showSnackbar(){
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = "Description created"
                )
            )
        }
    }
}