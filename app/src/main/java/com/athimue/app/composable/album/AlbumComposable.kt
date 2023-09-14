package com.athimue.app.composable.album

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
            PrimaryColorText(text = "${it.nbTracks} tracks")
            PrimaryColorText(text = "Genre id : ${it.genreId}")
        } ?: LoaderItem()
    }
}