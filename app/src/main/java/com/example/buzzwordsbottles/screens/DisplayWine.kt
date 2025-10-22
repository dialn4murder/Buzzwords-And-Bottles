package com.example.buzzwordsbottles.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.Descriptions

@Composable
fun DisplayWine(wineList: List<Descriptions> ){

    LazyVerticalGrid(
        modifier = Modifier
            .padding(6.dp),
        columns = GridCells.Fixed(1)
    ) {
        items(wineList) { description ->
            DescriptionCard(description)
        }
    }

}