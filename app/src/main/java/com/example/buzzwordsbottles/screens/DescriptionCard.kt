package com.example.buzzwordsbottles.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.R


@Composable
fun DescriptionCard(description: Descriptions){

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer)

        ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = description.title,
                    modifier = Modifier
                        .padding(3.dp),
                    textAlign = TextAlign.Start,
                )
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = description.description,
                    modifier = Modifier
                        .padding(3.dp),
                    textAlign = TextAlign.Start,
                )
            }
            Image(
                painter = painterResource(R.drawable.wine),
                contentDescription = "Wine Icon",
                modifier = Modifier
                    .size(height = 150.dp, width = 75.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.FillBounds
            )
        }
    }


}