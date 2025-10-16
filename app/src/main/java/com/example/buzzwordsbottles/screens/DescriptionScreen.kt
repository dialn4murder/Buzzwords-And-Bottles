package com.example.buzzwordsbottles.screens


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.buzzwordsbottles.classes.WineViewModel

@Composable
fun DescriptionsScreen(modifier: Modifier = Modifier, wineViewModel: WineViewModel){

    val text = wineViewModel.scannedText.observeAsState(emptyList())
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        items(text.value) { description->
            DescriptionCard(description)
        }
    }


}
