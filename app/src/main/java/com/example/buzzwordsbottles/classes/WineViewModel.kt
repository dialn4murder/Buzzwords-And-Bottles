package com.example.buzzwordsbottles.classes

import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WineViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()

    val initialList = listOf<Descriptions>(Descriptions(
        description = "A full-bodied red wine with notes of cherry, plum, and a hint of chocolate. Perfect with red meats.",
        title = "",
        score = 4.5f
    ),
        Descriptions(
            description = "A crisp and refreshing white wine with citrus aromas and a delicate floral finish. Ideal for summer evenings.",
            title = "",
            score = 4.2f
        ),
        Descriptions(
            description = "A deep ruby red with bold tannins and a lingering finish. Flavors of blackberry, cassis, and a touch of oak.",
            title = "",
            score = 4.8f
        ),
        Descriptions(
            description = "Light and aromatic rosé with strawberry and raspberry notes. Soft acidity makes it perfect for salads and seafood.",
            title = "",
            score = 4.1f
        ),
        Descriptions(
            description = "An elegant sparkling wine with fine bubbles, green apple aromas, and a crisp, clean finish. Ideal for celebrations.",
            title = "",
            score = 4.7f
        ),
        Descriptions(
            description = "A rich dessert wine with honeyed flavors, apricot notes, and a smooth, lingering sweetness. Best enjoyed chilled.",
            title = "",
            score = 4.6f
        ),
        Descriptions(
            description = "Medium-bodied red with earthy undertones and flavors of cherry and leather. Pairs well with roasted vegetables and meats.",
            title = "",
            score = 4.3f
        ),
        Descriptions(
            description = "A refreshing Sauvignon Blanc with zesty lime, passionfruit aromas, and a crisp mineral finish. Perfect for light dishes.",
            title = "",
            score = 4.4f
        ))

    val scannedText: LiveData<List<Descriptions>> get() = _scannedText

    fun setScannedText(descriptions: Descriptions){
        // Null safety if the initial scanned text is empty
        val currentList = _scannedText.value ?: initialList
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

//Descriptions(
//description = "A full-bodied red wine with notes of cherry, plum, and a hint of chocolate. Perfect with red meats.",
//title = "",
//score = 4.5f
//),
//Descriptions(
//description = "A crisp and refreshing white wine with citrus aromas and a delicate floral finish. Ideal for summer evenings.",
//title = "",
//score = 4.2f
//),
//Descriptions(
//description = "A deep ruby red with bold tannins and a lingering finish. Flavors of blackberry, cassis, and a touch of oak.",
//title = "",
//score = 4.8f
//),
//Descriptions(
//description = "Light and aromatic rosé with strawberry and raspberry notes. Soft acidity makes it perfect for salads and seafood.",
//title = "",
//score = 4.1f
//),
//Descriptions(
//description = "An elegant sparkling wine with fine bubbles, green apple aromas, and a crisp, clean finish. Ideal for celebrations.",
//title = "",
//score = 4.7f
//),
//Descriptions(
//description = "A rich dessert wine with honeyed flavors, apricot notes, and a smooth, lingering sweetness. Best enjoyed chilled.",
//title = "",
//score = 4.6f
//),
//Descriptions(
//description = "Medium-bodied red with earthy undertones and flavors of cherry and leather. Pairs well with roasted vegetables and meats.",
//title = "",
//score = 4.3f
//),
//Descriptions(
//description = "A refreshing Sauvignon Blanc with zesty lime, passionfruit aromas, and a crisp mineral finish. Perfect for light dishes.",
//title = "",
//score = 4.4f
//)