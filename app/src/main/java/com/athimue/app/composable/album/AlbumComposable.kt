package com.athimue.app.composable.album

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.composable.common.BackButton
import com.athimue.app.composable.common.CoverItem
import com.athimue.app.composable.common.LoaderItem
import com.athimue.app.composable.common.PrimaryColorText

@Composable
fun AlbumComposable(
    viewModel: AlbumViewModel = hiltViewModel(),
    albumId: Long,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadAlbum(albumId = albumId)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(onBack = onBack)
        uiState.album?.let {
            CoverItem(painter = rememberAsyncImagePainter(it.cover))
            Text(
                text = it.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            PrimaryColorText(text = "Artist : ${it.artist.name}")
            PrimaryColorText(text = "Release date : ${it.releaseDate}")
            PrimaryColorText(text = "Duration : ${it.duration}")
            PrimaryColorText(text = "Tracks : ${it.nbTracks}")
            PrimaryColorText(text = "Label : ${it.label}")
            PrimaryColorText(text = "Fans : ${it.nbFans}")
            PrimaryColorText(text = "Explicit lyrics ? : ${it.explicitLyrics}")
            PrimaryColorText(text = "Available ? : ${it.available}")
            PrimaryColorText(text = "Genres :")
            BulletList(
                items = it.genres.map { genre -> genre.name },
                modifier = Modifier.padding(8.dp),
                lineSpacing = 4.dp,
            )
            PrimaryColorText(text = "Tracks :")
            BulletList(
                items = it.tracks.map { track -> track.title },
                modifier = Modifier.padding(8.dp),
                lineSpacing = 4.dp,
            )
        } ?: LoaderItem(Modifier.fillMaxSize())
    }
}

@Composable
fun BulletList(
    modifier: Modifier = Modifier,
    indent: Dp = 20.dp,
    lineSpacing: Dp = 0.dp,
    items: List<String>,
) {
    Column(modifier = modifier) {
        items.forEach {
            Row {
                Text(
                    text = "\u2022",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.width(indent),
                )
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f, fill = true),
                )
            }
            if (lineSpacing > 0.dp && it != items.last()) {
                Spacer(modifier = Modifier.height(lineSpacing))
            }
        }
    }
}