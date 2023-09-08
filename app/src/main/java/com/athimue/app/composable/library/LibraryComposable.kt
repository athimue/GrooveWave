package com.athimue.app.composable.library

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.R
import com.athimue.app.composable.home.PopularTitle
import com.athimue.domain.model.Playlist

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LibraryComposable(
    viewModel: LibraryViewModel = hiltViewModel(),
    onPlaylistClick: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var isBottomSheetDisplayed by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(isBottomSheetDisplayed)
    var openDialog by remember { mutableStateOf(false) }
    var playlistName by rememberSaveable { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isBottomSheetDisplayed = !isBottomSheetDisplayed },
                containerColor = MaterialTheme.colorScheme.secondary,
            ) {
                Icon(Icons.Rounded.Add, "Add playlist")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PopularTitle(title = "Your library")
            uiState.playlist?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(
                        items = it,
                        key = { item -> item.id }) {
                        val currentItem by rememberUpdatedState(it)
                        val dismissState = rememberDismissState(
                            confirmValueChange = { dismissValue ->
                                when (dismissValue) {
                                    DismissValue.DismissedToEnd -> {
                                        onEditPlaylist(currentItem.id)
                                        true
                                    }
                                    DismissValue.DismissedToStart -> {
                                        viewModel.deletePlaylist(currentItem.id)
                                        true
                                    }
                                    else -> false
                                }
                            }
                        )
                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier
                                .padding(vertical = 1.dp)
                                .animateItemPlacement(),
                            background = {
                                SwipeBackground(dismissState)
                            },
                            dismissContent = {
                                PlaylistItemRow(it, onPlaylistClick)
                            }
                        )
                    }
                }
            } ?: Text(
                modifier = Modifier.fillMaxWidth(),
                text = "No playlist",
                textAlign = TextAlign.Center
            )
            if (isBottomSheetDisplayed) {
                ModalBottomSheet(
                    onDismissRequest = { isBottomSheetDisplayed = false },
                    sheetState = bottomSheetState
                )
                {
                    PlaylistModal(
                        displayModal = {
                            isBottomSheetDisplayed = false
                            openDialog = true
                        },
                        displayBottomSheet = { isBottomSheetDisplayed = true }
                    )
                }
            }
            if (openDialog) {
                PlaylistDialog(
                    playlistName = playlistName,
                    onCreatePlaylist = { viewModel.createPlaylist(it) },
                    updatePlaylistName = { playlistName = it },
                    openDialog = { openDialog = false },
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PlaylistDialog(
    playlistName: String,
    onCreatePlaylist: (String) -> Unit,
    updatePlaylistName: (String) -> Unit,
    openDialog: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = openDialog
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Give your playlist a name",
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = playlistName,
                    onValueChange = { updatePlaylistName.invoke(it) },
                    label = { Text("Playlist name") },
                    placeholder = { Text("My playlist...") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        openDialog.invoke()
                        onCreatePlaylist(playlistName)
                        updatePlaylistName.invoke("")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Create")
                }
            }
        }
    }
}

@Composable
private fun PlaylistModal(
    displayModal: () -> Unit,
    displayBottomSheet: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PopularTitle(title = "Playlist menu")
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    displayBottomSheet.invoke()
                    displayModal.invoke()
                }) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = ""
                )
                Text(
                    text = "Create playlist",
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 30.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = displayBottomSheet
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = ""
                )
                Text(
                    text = "Options",
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return

    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.White
            DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.primary
            DismissValue.DismissedToStart -> MaterialTheme.colorScheme.secondary
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Rounded.Edit
        DismissDirection.EndToStart -> Icons.Rounded.Delete
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Delete Icon",
            modifier = Modifier.scale(if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f)
        )
    }
}

@Composable
private fun PlaylistItemRow(it: Playlist, onPlaylistClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .clickable { onPlaylistClick(it.id) }
            .padding(vertical = 5.dp)
            .padding(horizontal = 5.dp)
    ) {
        Image(
            painter = if (it.tracks.isNotEmpty()) rememberAsyncImagePainter(
                it.tracks[0].cover
            )
            else {
                painterResource(id = R.drawable.playlist_cover)
            },
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 3.dp)
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = it.name)
            Text(text = it.id.toString())
            Text(text = "Tracks nb : " + it.tracks.size.toString())
        }
    }
}
