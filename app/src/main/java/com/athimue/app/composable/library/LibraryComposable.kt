package com.athimue.app.composable.library

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.app.R
import com.athimue.app.composable.common.TitleText

@OptIn(ExperimentalMaterial3Api::class)
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
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            TitleText(title = "Your library")
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = uiState.playlists, key = { item -> item.id }) { libraryUiModel ->
                    LibrarySwipeToDismiss(libraryUiModel = libraryUiModel,
                        onPlaylistClick = onPlaylistClick,
                        onEditPlaylist = onEditPlaylist,
                        onDeletePlaylist = { playlistId -> viewModel.deletePlaylist(playlistId) })
                }
            }
            if (isBottomSheetDisplayed) {
                ModalBottomSheet(
                    onDismissRequest = { isBottomSheetDisplayed = false },
                    sheetState = bottomSheetState
                ) {
                    PlaylistModal(displayModal = {
                        isBottomSheetDisplayed = false
                        openDialog = true
                    }, displayBottomSheet = { isBottomSheetDisplayed = true })
                }
            }
            if (openDialog) {
                val context = LocalContext.current
                PlaylistDialog(
                    playlistName = playlistName,
                    onCreatePlaylist = { name ->
                        Toast.makeText(
                            context, "Playlist added !", Toast.LENGTH_SHORT
                        ).show()
                        viewModel.createPlaylist(name)
                    },
                    updatePlaylistName = { name -> playlistName = name },
                    openDialog = { openDialog = false },
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
private fun LazyItemScope.LibrarySwipeToDismiss(
    libraryUiModel: LibraryUiModel,
    onPlaylistClick: (Int) -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onDeletePlaylist: (Int) -> Unit
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(libraryUiModel)
    val dismissState = rememberDismissState(confirmValueChange = { dismissValue ->
        when (dismissValue) {
            DismissValue.DismissedToEnd -> {
                onEditPlaylist(currentItem.id)
                true
            }

            DismissValue.DismissedToStart -> {
                Toast.makeText(
                    context, "Playlist deleted !", Toast.LENGTH_SHORT
                ).show()
                onDeletePlaylist(currentItem.id)
                true
            }

            else -> false
        }
    })
    SwipeToDismiss(state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp)
            .animateItemPlacement(),
        background = {
            SwipeBackground(dismissState)
        },
        dismissContent = {
            PlaylistItemRow(libraryUiModel, onPlaylistClick)
        })
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
                TextField(value = playlistName,
                    onValueChange = { updatePlaylistName.invoke(it) },
                    label = { Text("Playlist name") },
                    placeholder = { Text("My playlist...") })
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        openDialog.invoke()
                        onCreatePlaylist(playlistName)
                        updatePlaylistName.invoke("")
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Create")
                }
            }
        }
    }
}

@Composable
private fun PlaylistModal(
    displayModal: () -> Unit, displayBottomSheet: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TitleText(title = "Playlist menu")
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                displayBottomSheet.invoke()
                displayModal.invoke()
            }) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle, contentDescription = ""
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
                modifier = Modifier.fillMaxWidth(), onClick = displayBottomSheet
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings, contentDescription = ""
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
        }, label = "color animation"
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
private fun PlaylistItemRow(libraryUiModel: LibraryUiModel, onPlaylistClick: (Int) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colorScheme.primaryContainer
        )
        .clickable { onPlaylistClick(libraryUiModel.id) }
        .padding(vertical = 5.dp)
        .padding(horizontal = 5.dp)) {
        Image(
            painter = painterResource(
                id = R.drawable.playlist_cover
            ),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 3.dp)
        )
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = libraryUiModel.name, color = MaterialTheme.colorScheme.primary)
            Text(
                text = "${libraryUiModel.tracksSize} songs",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
