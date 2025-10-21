package com.example.buzzwordsbottles.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.classes.WineViewModel
import com.example.compose.outlineVariantLight
import com.google.android.material.search.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionsScreen(modifier: Modifier = Modifier, wineViewModel: WineViewModel){

    val text = wineViewModel.scannedText.observeAsState(emptyList())
    var expanded: (Boolean) -> Unit = { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    dividerColor = MaterialTheme.colorScheme.surfaceVariant

                ),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = "Search",
                        onQueryChange = {

                        },
                        onSearch = {
                            //TODO
                        },
                        expanded = true,
                        onExpandedChange = expanded
                    )
                },

                expanded = false,
                onExpandedChange = expanded,
                shape = SearchBarDefaults.inputFieldShape,
                tonalElevation = SearchBarDefaults.TonalElevation,
                shadowElevation = SearchBarDefaults.ShadowElevation,
                windowInsets = SearchBarDefaults.windowInsets,
                content = { },
            )
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(6.dp),
            columns = GridCells.Fixed(1)
        ) {
            items(text.value) { description ->
                DescriptionCard(description)
            }
        }
    }


}
