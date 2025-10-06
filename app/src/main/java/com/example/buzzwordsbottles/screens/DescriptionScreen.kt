package com.example.buzzwordsbottles.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionsScreen(modifier: Modifier = Modifier){

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(100) { i->
            Box(
                modifier = modifier
                    .width(128.dp)
                    .height(128.dp)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Item $i")
            }
        }
    }


}
