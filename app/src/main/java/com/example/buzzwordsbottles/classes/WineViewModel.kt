package com.example.buzzwordsbottles.classes

import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WineViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()

    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(descriptions: Descriptions){
        // Null safety if the initial scanned text is empty
        val currentList = _scannedText.value ?: emptyList()
        // Adds the scanned text to the lazy list
        _scannedText.value = currentList + descriptions
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