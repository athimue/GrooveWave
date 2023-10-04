package com.athimue.app.composable.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.R
import com.athimue.app.R.string
import com.athimue.app.composable.common.LoaderItem
import com.athimue.app.composable.common.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComposable(
    viewModel: SearchViewModel = hiltViewModel(),
    onClick: (String, Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val filters = listOf("Track", "Album", "Artist", "Playlist", "Podcast", "Radio")
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var isBottomSheetDisplayed by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(isBottomSheetDisplayed)
    var playlistSelected by remember { mutableStateOf(-1) }
    var trackSelected by remember { mutableStateOf(-1L) }
    var filterSelected by remember { mutableStateOf("Track") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchBar(query) {
            query = it
            viewModel.searchRequested(filterSelected, it.text)
        }
        SearchFilter(filters, filterSelected) {
            filterSelected = it
            viewModel.searchRequested(filterSelected, query.text)
        }
        if (query.text.isBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Recent searches",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
        } else {
            if (uiState.isLoading) {
                LoaderItem(Modifier.fillMaxSize())
            } else SearchGrid(
                searchResults = uiState.searchResult,
                isPlaylistBtnDisplayed = filterSelected == "Track",
                onPlaylistBtnClick = {
                    trackSelected = it
                    isBottomSheetDisplayed = true
                },
                onFavBtnClick = {
                    Toast.makeText(context, "Song added to Favorites", Toast.LENGTH_SHORT).show()
                    viewModel.addFavorite(filterSelected, it)
                },
                onClick = { onClick(filterSelected, it) },
            )
        }
        if (isBottomSheetDisplayed) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetDisplayed = false }, sheetState = bottomSheetState
            ) {
                Scaffold(floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
                    Button(onClick = {
                        Toast.makeText(context, "Track added to playlist !", Toast.LENGTH_SHORT)
                            .show()
                        viewModel.addTrackToPlaylist(playlistSelected, trackSelected)
                        isBottomSheetDisplayed = false
                    }) {
                        Text(text = "Done")
                    }
                }) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 10.dp, vertical = 20.dp
                                )
                        ) {
                            Text(
                                modifier = Modifier.clickable {
                                    isBottomSheetDisplayed = false
                                }, color = MaterialTheme.colorScheme.primary, text = "Cancel"
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = "Add to playlist",
                                color = MaterialTheme.colorScheme.primary,
                                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Divider()
                        LazyColumn {
                            items(items = uiState.playlists) {
                                Row(modifier = Modifier.clickable { playlistSelected = it.id }) {
                                    Image(
                                        painter = if (it.trackUiModels.isNotEmpty()) rememberAsyncImagePainter(
                                            it.trackUiModels[0].cover
                                        )
                                        else {
                                            painterResource(id = R.drawable.playlist_cover)
                                        },
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(75.dp)
                                            .padding(top = 4.dp, bottom = 4.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .padding(bottom = 3.dp)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                            .weight(1f)
                                            .align(Alignment.CenterVertically),
                                    ) {
                                        Text(text = it.name)
                                        Text(text = "${it.trackUiModels.size} songs")
                                    }
                                    RadioButton(modifier = Modifier.align(Alignment.CenterVertically),
                                        selected = (it.id == playlistSelected),
                                        onClick = { playlistSelected = it.id })
                                }
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(query: TextFieldValue, onQueryChange: (TextFieldValue) -> Unit) {
    Column {
        TitleText(title = stringResource(id = string.search))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp),
            tonalElevation = 5.dp,
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            TextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                value = query,
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    if (!it.text.contains("\n")) {
                        onQueryChange(it)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SearchFilter(
    filters: List<String>, filterSelected: String, onFilterChange: (String) -> Unit
) {
    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        items(filters) {
            Column {
                FilterChip(selected = (it == filterSelected),
                    modifier = Modifier.padding(horizontal = 5.dp),
                    leadingIcon = {
                        if (it == filterSelected) {
                            Icon(
                                imageVector = Icons.Filled.Check, contentDescription = "Check"
                            )
                        }
                    },
                    label = { Text(text = it) },
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = MaterialTheme.colorScheme.secondary,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = MaterialTheme.colorScheme.secondary,
                    ),
                    onClick = { onFilterChange(it) })
            }
        }
    }
}

@Composable
fun ColumnScope.SearchGrid(
    searchResults: List<SearchResultModel>,
    isPlaylistBtnDisplayed: Boolean,
    onPlaylistBtnClick: (Long) -> Unit,
    onFavBtnClick: (Long) -> Unit,
    onClick: (Long) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(searchResults) { item ->
            Row(modifier = Modifier
                .padding(vertical = 5.dp)
                .padding(horizontal = 5.dp)
                .clickable { onClick(item.id) }) {
                Image(
                    painter = rememberAsyncImagePainter(item.picture),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = item.title, color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = item.subTitle, color = MaterialTheme.colorScheme.secondary
                    )
                }
                Button(modifier = Modifier.padding(5.dp), onClick = { onFavBtnClick(item.id) }) {
                    Image(
                        imageVector = Icons.Rounded.Favorite,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondaryContainer),
                        contentDescription = ""
                    )
                }
                if (isPlaylistBtnDisplayed) {
                    Button(modifier = Modifier.padding(5.dp),
                        onClick = { onPlaylistBtnClick(item.id) }) {
                        Image(
                            imageVector = Icons.Rounded.Menu,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondaryContainer),
                            contentDescription = ""
                        )
                    }
                }
            }
            Divider()
        }
    }
}