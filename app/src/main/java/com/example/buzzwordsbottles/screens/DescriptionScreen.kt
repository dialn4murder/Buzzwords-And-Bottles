package com.example.buzzwordsbottles.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.example.buzzwordsbottles.classes.WineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionsScreen(wineViewModel: WineViewModel){

    var textFieldState = rememberTextFieldState()
    var search = ""

    val wine = wineViewModel.scannedText.observeAsState(emptyList())
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        SearchBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .semantics {traversalIndex = 0f},
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
                        search = textFieldState.text.toString()
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it},
                    placeholder = { Text("Search") }
                )
            },

            expanded = expanded,
            onExpandedChange = { it : Boolean ->
                expanded = it}
        ){
            var filteredList = wine.value.filter {
                it.description.contains(search)
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(6.dp),
                columns = GridCells.Fixed(1)
            ) {
                items(filteredList) { description ->
                    DescriptionCard(description)
                }
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(6.dp),
            columns = GridCells.Fixed(1)
        ) {
            items(wine.value) { description ->
                DescriptionCard(description)
            }
        }

    }


}
