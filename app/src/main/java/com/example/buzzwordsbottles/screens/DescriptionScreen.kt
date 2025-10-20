package com.example.buzzwordsbottles.screens


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.classes.WineViewModel
import com.google.android.material.search.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionsScreen(modifier: Modifier = Modifier, wineViewModel: WineViewModel){

    val text = wineViewModel.scannedText.observeAsState(emptyList())
    var expanded: (Boolean) -> Unit = { mutableStateOf(false) }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = "Search",
                onQueryChange = {

            },
                onSearch = {
                //TODO
            },
                expanded = true,
                onExpandedChange = expanded,
            )
        },
        expanded = true,
        onExpandedChange = expanded,
        shape = SearchBarDefaults.inputFieldShape,
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content = { },
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        items(text.value) { description->
            DescriptionCard(description)
        }
    }


}
