package com.athimue.app.composable.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.R
import com.athimue.app.composable.common.LoaderItem
import com.athimue.app.composable.common.TitleText

@Composable
fun HomeComposable(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onPopularTrackClick: (Long) -> Unit,
    onPopularAlbumClick: (Long) -> Unit,
    onPopularArtistClick: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeComposableContent(
        uiState = uiState,
        modifier = modifier,
        onPopularTrackClick = onPopularTrackClick,
        onPopularAlbumClick = onPopularAlbumClick,
        onPopularArtistClick = onPopularArtistClick
    )
}

@Composable
fun HomeComposableContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onPopularTrackClick: (Long) -> Unit,
    onPopularAlbumClick: (Long) -> Unit,
    onPopularArtistClick: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            text = stringResource(id = R.string.app_name),
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            HomeLazyRow(
                R.string.popular_tracks,
                R.string.no_popular_tracks,
                uiState.tracks,
                onPopularTrackClick
            )
            HomeLazyRow(
                R.string.popular_albums,
                R.string.no_popular_albums,
                uiState.albums,
                onPopularAlbumClick
            )
            HomeLazyRow(
                R.string.popular_artists,
                R.string.no_popular_artists,
                uiState.artists,
                onPopularArtistClick
            )
        }
    }
}

@Composable
fun ColumnScope.HomeLazyRow(
    titleId: Int,
    errorTitle: Int,
    items: List<LazyRowItemModel>?,
    onTrackClick: (Long) -> Unit
) {
    TitleText(stringResource(id = titleId))
    items?.let {
        if (items.isEmpty()) {
            ErrorTitle(stringResource(id = errorTitle))
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                items(it) { item ->
                    LazyRowItem(
                        item = item,
                        onTrackClick = onTrackClick
                    )
                }
            }
        }
    } ?: LoaderItem(Modifier.fillMaxWidth())
}

@Composable
fun ErrorTitle(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 15.sp,
        fontFamily = FontFamily.Monospace,
        text = title
    )
}

@Composable
fun LazyRowItem(
    item: LazyRowItemModel,
    onTrackClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onTrackClick(item.id) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.picture),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 3.dp)
        )
        Text(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Light,
            text = if (item.title.length > 10) item.title.substring(0, 8) + ".." else item.title
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
}