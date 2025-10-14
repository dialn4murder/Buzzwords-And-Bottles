package com.example.buzzwordsbottles.screens


import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.TextAnalysisViewModel

@Composable
fun DescriptionsScreen(modifier: Modifier = Modifier, textAnalysisViewModel: TextAnalysisViewModel){

    val text = textAnalysisViewModel.scannedText.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 384.dp)
    ) {
        items(text.value) { description->
//            Box(
//                modifier = modifier
//                    .width(128.dp)
//                    .height(128.dp)
//                    .padding(2.dp)
//                    .clip(RoundedCornerShape(5.dp))
//                    .background(MaterialTheme.colorScheme.primaryContainer),
//                contentAlignment = Alignment.Center
//            ){
//                Text(text = i.description,
//                    color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodySmall)
//
//            }
            DescriptionCard(description)
        }
    }


}
