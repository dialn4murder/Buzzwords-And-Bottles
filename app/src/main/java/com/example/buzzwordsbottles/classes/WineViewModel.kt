package com.example.buzzwordsbottles.classes

import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WineViewModel : ViewModel() {
    private val _scannedText = MutableLiveData<List<Descriptions>>()
    private val _searchedText = MutableLiveData<List<Descriptions>>()

    private val initialList = listOf<Descriptions>(
            Descriptions(
                title = "Cabernet Sauvignon Reserve",
                description = "A full-bodied red wine with notes of cherry, plum, and a hint of chocolate. Perfect with red meats and rich sauces.",
                score = 4.5f
            ),
        Descriptions(
            title = "Chardonnay Vintage 2020",
            description = "A crisp and refreshing white wine with citrus aromas, buttery texture, and a delicate floral finish. Ideal for summer evenings.",
            score = 4.2f
        ),
        Descriptions(
            title = "Merlot Grand Estate",
            description = "A deep ruby red with bold tannins and a lingering finish. Flavors of blackberry, cassis, and a touch of oak for a smooth, velvety taste.",
            score = 4.8f
        ),
        Descriptions(
            title = "Provence Rosé",
            description = "Light and aromatic rosé with strawberry and raspberry notes. Soft acidity makes it perfect for salads, seafood, or sunny afternoons.",
            score = 4.1f
        ),
        Descriptions(
            title = "Brut Sparkling Cuvée",
            description = "An elegant sparkling wine with fine bubbles, green apple aromas, and a crisp, clean finish. Ideal for celebrations or brunch.",
            score = 4.7f
        ),
        Descriptions(
            title = "Late Harvest Riesling",
            description = "A rich dessert wine with honeyed flavors, apricot notes, and a smooth, lingering sweetness. Best enjoyed chilled after dinner.",
            score = 4.6f
        ),
        Descriptions(
            title = "Pinot Noir Classic",
            description = "Medium-bodied red with earthy undertones and flavors of cherry and leather. Pairs well with roasted vegetables and game meats.",
            score = 4.3f
        ),
        Descriptions(
            title = "Sauvignon Blanc Zest",
            description = "A refreshing Sauvignon Blanc with zesty lime, passionfruit aromas, and a crisp mineral finish. Perfect for seafood and light dishes.",
            score = 4.4f
        ),
        Descriptions(
            title = "Malbec Reserve Mendoza",
            description = "A dark, full-bodied Malbec bursting with flavors of black plum and cocoa, balanced by a subtle smoky oak finish.",
            score = 4.6f
        ),
        Descriptions(
            title = "Pinot Grigio Dolce",
            description = "Bright and refreshing, this white wine offers notes of pear, green apple, and lemon zest with a clean, smooth finish.",
            score = 4.0f
        ))

    val scannedText: LiveData<List<Descriptions>> get() = _scannedText
    val searchedText: LiveData<List<Descriptions>> get() = _searchedText

    fun setScannedText(descriptions: Descriptions){
        // Null safety if the initial scanned text is empty
        val currentList = _scannedText.value ?: emptyList()
        // Adds the scanned text to the lazy list
        _scannedText.value = currentList + descriptions
        showSnackbar()
    }

    fun setSearchedText(descriptions: Descriptions){
        // Null safety if the initial scanned text is empty
        val currentList = _searchedText.value ?: emptyList()
        // Adds the scanned text to the lazy list
        _searchedText.value = currentList + descriptions
    }

    fun clearSearchedText(){
        _searchedText.value = emptyList()
    }

    fun initialiseList(){
        for (description in initialList){
            setScannedText(description)
        }
    }

    fun filterList(searchItem: String){
        val filteredList = _scannedText.value?.filter { wineFromList ->
            wineFromList.title.contains(searchItem)
        }

        if (filteredList != null) {
            for (wine in filteredList) {
                setSearchedText(wine)
            }
        }
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
