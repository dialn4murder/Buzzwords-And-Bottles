package com.example.buzzwordsbottles.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.Descriptions

@Composable
fun DescriptionCard(description: Descriptions){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
            .padding(6.dp)

    ) {

        Column{
        Text(
            text = description.title,
            modifier = Modifier
                .padding(3.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = description.description,
            modifier = Modifier
                .padding(3.dp),
            textAlign = TextAlign.Center,
        )
            }
    }

}