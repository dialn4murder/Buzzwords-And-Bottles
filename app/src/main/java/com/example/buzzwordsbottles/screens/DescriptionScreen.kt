package com.example.buzzwordsbottles.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.buzzwordsbottles.classes.WineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionsScreen(wineViewModel: WineViewModel){

    val wine = wineViewModel.scannedText.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        DescriptionSearchBar( wineViewModel)

        DisplayWine(wine.value)

    }

}


