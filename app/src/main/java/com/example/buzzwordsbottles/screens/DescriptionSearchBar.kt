package com.example.buzzwordsbottles.screens

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.classes.WineViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ColumnScope.DescriptionSearchBar(
    wineViewModel: WineViewModel

) {
    var textFieldState = rememberTextFieldState()
    var search = ""
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .semantics { traversalIndex = 0f },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            dividerColor = MaterialTheme.colorScheme.surfaceVariant

        ),
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = {
                    textFieldState.edit {
                        replace(0, length, it)
                    }
                },
                onSearch = {
                    search = it
                    wineViewModel.clearSearchedText()
                    val filteredList = wineViewModel.scannedText.value?.filter { wineFromList ->
                        wineFromList.title.contains(search)
                    }

                    if (filteredList != null) {
                        for (wine in filteredList) {
                            Log.d("wine", wine.title)
                            wineViewModel.setSearchedText(wine)
                        }
                    }

                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text("Search") }
            )
        },

        expanded = expanded,
        onExpandedChange = { it: Boolean ->
            expanded = it
        }
    ) {
        DisplayWine(wineViewModel.searchedText.value ?: wineViewModel.scannedText.value)
    }
}