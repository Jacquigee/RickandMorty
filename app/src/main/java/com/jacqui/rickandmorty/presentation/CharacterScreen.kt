package com.jacqui.rickandmorty.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.jacqui.rickandmorty.R
import com.jacqui.rickandmorty.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.presentation.component.CharacterListItem
import com.jacqui.rickandmorty.presentation.viewmodel.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 4:31 PM
 */

@Composable
fun CharacterScreen(
    onCharacterClick: (CharacterResultDomain) -> Unit,
) {
    val viewmodel: CharacterViewModel = koinViewModel()
    val characters = viewmodel.characters.collectAsLazyPagingItems()

    CharacterScreenContent(
        characters = characters,
        characterDetails = onCharacterClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreenContent(
    characters: LazyPagingItems<CharacterResultDomain>,
    characterDetails: (CharacterResultDomain) -> Unit
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
            when {
                characters.loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicator()
                }

                characters.loadState.refresh is LoadState.Error -> {
                    val error = (characters.loadState.refresh as LoadState.Error).error
                    Text(
                        text = error.message ?: "Unknown error",
                    )
                }

                else -> {
                    LazyColumn {
                        items(
                            characters.itemCount,
                            key = characters.itemKey { it.id }) { index ->
                            val character = characters[index]
                            if (character != null) {
                                CharacterListItem(
                                    character = character,
                                    onCharacterClicked = { characterDetails(character) }
                                )
                            }
                        }
                        item {
                            if (characters.loadState.append is LoadState.Loading) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                }
            }
        }
    }

}
