package com.athimue.app.composable.track

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            PrimaryColorText(text = "Duration : ${it.duration / 60} min ${it.duration % 60}")
            PrimaryColorText(text = "Leaderboard : ${it.rank}")
            PrimaryColorText(text = "Explicit ? : ${it.explicitLyrics}")
            Divider(modifier = Modifier.padding(vertical = 5.dp))
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Album", textAlign = TextAlign.Center)
                    Image(
                        painter = rememberAsyncImagePainter(it.album.cover),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 4.dp, bottom = 4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(bottom = 3.dp)
                    )
                    Text(text = it.album.name)
                    Text(text = it.album.id.toString())
                    Text(text = "Track position  : ${it.position}")
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Artist", textAlign = TextAlign.Center)
                    Image(
                        painter = rememberAsyncImagePainter(it.artist.cover),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 4.dp, bottom = 4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(bottom = 3.dp)
                    )
                    Text(text = it.artist.name)
                    Text(text = it.artist.id.toString())
                }
            }
        } ?: LoaderItem()
    }
}