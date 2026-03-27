package com.jacqui.rickandmorty.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jacqui.rickandmorty.R
import com.jacqui.rickandmorty.view.component.CharacterListItem
import com.jacqui.rickandmorty.view.viewmodel.CharacterUiState
import com.jacqui.rickandmorty.view.viewmodel.CharacterViewModel
import com.jacqui.rickandmorty.view.viewmodel.ScreenUIState
import org.koin.compose.viewmodel.koinViewModel

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 4:31 PM
 */

@Composable
fun CharacterScreen(modifier: Modifier = Modifier) {
    val viewmodel: CharacterViewModel = koinViewModel()
    val uiState by viewmodel.characterUiState.collectAsState()

    CharacterScreenContent(
        state = uiState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreenContent(
    state: CharacterUiState,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (val result = state.characters) {
                is ScreenUIState.Error -> {
                    Text(text = result.message)
                }

                ScreenUIState.Loading -> {
                    CircularProgressIndicator()
                }

                is ScreenUIState.Success -> {
                    val listItem = result.data.results
                    LazyColumn {
                        items(listItem) { character ->
                            CharacterListItem(
                                character = character,
                            )
                        }
                    }
                }
            }
        }
    }

}