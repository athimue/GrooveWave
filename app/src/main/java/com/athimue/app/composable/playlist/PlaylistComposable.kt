package com.athimue.app.composable.playlist

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.R
import com.athimue.app.composable.common.BackButton
import com.athimue.app.composable.common.CoverItem
import com.athimue.app.composable.common.LoaderItem
import com.athimue.app.composable.common.PrimaryColorText

@Composable
fun PlaylistComposable(
    viewModel: PlaylistViewModel = hiltViewModel(),
    playlistId: Int,
    onBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    viewModel.loadPlaylist(playlistId)

    Column {
        uiState.value.playlist?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp
                    )
            ) {
                BackButton(onBack)
                CoverItem(
                    painter = if (it.trackUiModels.isNotEmpty())
                        rememberAsyncImagePainter(it.trackUiModels[0].cover)
                    else {
                        painterResource(id = R.drawable.playlist_cover)
                    }
                )
                PlaylistItemText(
                    text = it.name
                )
                PlaylistItemText(
                    text = if (it.trackUiModels.isNotEmpty()) {
                        "${
                            it.trackUiModels.map { it.duration }.reduce { acc, dur -> acc + dur }
                        } sec"
                    } else "0 sec")
                Divider(modifier = Modifier.padding(start = 10.dp, top = 5.dp))
                if (it.trackUiModels.isNotEmpty()) {
                    PlaylistTracksLazyColumn(
                        trackUiModels = it.trackUiModels,
                        trackPlaying = uiState.value.trackUrlPlayed,
                        playlistId = playlistId,
                        onSwipeToDismiss = { trackId, playListId ->
                            viewModel.deletePlaylistTrack(
                                trackId = trackId,
                                playlistId = playListId
                            )
                        },
                        onPlayClick = { viewModel.playTrack(it) }
                    )
                } else
                    NoTrackText()
            }
        } ?: LoaderItem(Modifier.fillMaxSize())
    }
}

@Composable
private fun PlaylistTracksLazyColumn(
    trackUiModels: List<TrackUiModel>,
    playlistId: Int,
    trackPlaying: String?,
    onSwipeToDismiss: (Long, Int) -> Unit,
    onPlayClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
    ) {
        items(items = trackUiModels,
            key = { item -> item.id }
        ) {
            PlaylistSwipeToDismissItem(
                modifier = Modifier,
                onSwipeToDismiss = onSwipeToDismiss,
                trackUiModel = it,
                trackPlaying = trackPlaying,
                playlistId = playlistId,
                onPlayClick = onPlayClick
            )
        }
    }
}

@Composable
private fun PlaylistItemText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
        color = MaterialTheme.colorScheme.primary,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
private fun LazyItemScope.PlaylistSwipeToDismissItem(
    modifier: Modifier,
    onSwipeToDismiss: (Long, Int) -> Unit,
    trackUiModel: TrackUiModel,
    trackPlaying: String?,
    playlistId: Int,
    onPlayClick: (String) -> Unit
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(trackUiModel)
    val dismissState = rememberDismissState(
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                DismissValue.DismissedToStart -> {
                    Toast.makeText(
                        context,
                        "Track deleted !",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    onSwipeToDismiss(currentItem.id, playlistId)
                    true
                }

                else -> false
            }
        }
    )
    SwipeToDismiss(
        state = dismissState,
        modifier = modifier
            .padding(vertical = 1.dp)
            .animateItemPlacement(),
        background = {
            DeleteSwipeBackground(dismissState)
        },
        directions = setOf(DismissDirection.EndToStart),
        dismissContent = {
            TrackRowItem(trackUiModel, trackPlaying, onPlayClick)
        }
    )
}

@Composable
private fun TrackRowItem(
    trackUiModel: TrackUiModel,
    trackPlaying: String?,
    onPlayClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(horizontal = 5.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(trackUiModel.cover),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(CenterVertically)
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            PrimaryColorText(trackUiModel.title)
            PrimaryColorText(trackUiModel.albumName)
            PrimaryColorText(trackUiModel.artistName)
        }
        Button(
            onClick = { onPlayClick(trackUiModel.preview) },
            modifier = Modifier.align(CenterVertically),
        ) {
            Image(
                imageVector = if (trackUiModel.preview == trackPlaying) Icons.Rounded.Refresh else Icons.Rounded.PlayArrow,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                contentDescription = "",
            )
        }
    }
    Divider()
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DeleteSwipeBackground(dismissState: DismissState) {
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.White
            DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.primary
            DismissValue.DismissedToStart -> MaterialTheme.colorScheme.secondary
        }, label = "color animation"
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = "Delete Icon",
            modifier = Modifier.scale(if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f)
        )
    }
}

@Composable
private fun NoTrackText() {
    Text(
        text = "No tracks inside this playlist.",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp)
    )
}