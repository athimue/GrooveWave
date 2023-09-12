package com.athimue.app.composable.playlist

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
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
import com.athimue.domain.model.Track

@Composable
fun PlaylistComposable(
    viewModel: PlaylistViewModel = hiltViewModel(),
    playlistId: Int,
    onBack: () -> Unit,
) {
    viewModel.loadPlaylist(playlistId)
    val uiState = viewModel.uiState.collectAsState()
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
                    painter = if (it.tracks.isNotEmpty())
                        rememberAsyncImagePainter(it.tracks[0].cover)
                    else {
                        painterResource(id = R.drawable.playlist_cover)
                    }
                )
                PlaylistItemText(
                    text = it.name
                )
                PlaylistItemText(
                    text = if (it.tracks.isNotEmpty()) {
                        "${
                            it.tracks.map { it.duration }.reduce { acc, dur -> acc + dur }
                        } sec"
                    } else "0 sec")
                Divider(modifier = Modifier.padding(start = 10.dp, top = 5.dp))
                if (it.tracks.isNotEmpty()) {
                    PlaylistTracksLazyColumn(
                        tracks = it.tracks,
                        playlistId = playlistId,
                        onSwipeToDismiss = { trackId, playListId ->
                            viewModel.deletePlaylistTrack(
                                trackId = trackId,
                                playlistId = playListId
                            )
                        }
                    )
                } else
                    NoTrackText()
            }
        } ?: LoaderItem()
    }
}

@Composable
private fun PlaylistTracksLazyColumn(
    tracks: List<Track>,
    playlistId: Int,
    onSwipeToDismiss: (Long, Int) -> Unit,
) {
    Log.d("COUCOU", tracks.toString())
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
    ) {
        items(items = tracks,
            key = { item -> item.id }
        ) {
            PlaylistSwipeToDismissItem(
                modifier = Modifier,
                onSwipeToDismiss = onSwipeToDismiss,
                track = it,
                playlistId = playlistId
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
    track: Track,
    playlistId: Int
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(track)
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
            TrackRowItem(track)
        }
    )
}

@Composable
private fun TrackRowItem(it: Track) {
    val mediaPlayer = MediaPlayer()
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(horizontal = 5.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(it.cover),
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
            PrimaryColorText(it.title)
            PrimaryColorText(it.album.name)
            PrimaryColorText(it.artist.name)
        }
        Button(
            onClick = {
                mediaPlayer.setAudioAttributes(
                    AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                try {
                    mediaPlayer.setDataSource(it.preview)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            modifier = Modifier.align(CenterVertically),
        ) {
            Image(
                imageVector = Icons.Rounded.PlayArrow,
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
        }
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