package com.athimue.app.composable.track

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.composable.common.BackButton
import com.athimue.app.composable.common.CoverItem
import com.athimue.app.composable.common.LoaderItem
import com.athimue.app.composable.common.PrimaryColorText

@Composable
fun TrackComposable(
    viewModel: TrackViewModel = hiltViewModel(),
    trackId: Long,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadTrack(trackId = trackId)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        BackButton(onBack = onBack)
        uiState.track?.let {
            CoverItem(painter = rememberAsyncImagePainter(it.cover))
            Text(
                text = it.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Album : ${it.album.name}",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Artist : ${it.artist.name}",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.secondary
            )
            PrimaryColorText(text = it.duration.toString())
            PrimaryColorText(text = it.rank.toString())
            PrimaryColorText(text = it.position.toString())
            PrimaryColorText(text = it.explicitLyrics.toString())
        } ?: LoaderItem()
    }
}