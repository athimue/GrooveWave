package com.athimue.app.composable.favorites

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.app.composable.common.TitleText
import com.athimue.app.composable.search.SearchResultModel

@Composable
internal fun FavoritesComposable(viewModel: FavoritesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val titles = listOf("Tracks", "Artists", "Albums")
    var tabSelected by remember { mutableIntStateOf(0) }
    Column {
        TitleText(
            title = "Favorites"
        )
        FavoriteTabRow(
            tabSelected = tabSelected,
            onTabSelected = { tabSelected = it },
            titles = titles
        )
        FavoriteLazyColumn(
            tabSelected = tabSelected,
            uiState = uiState,
            removeFavorite = {
                Toast.makeText(
                    context,
                    "Item removed from Favorites",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.removeFavorite(titles[tabSelected], it)
            }
        )
    }
}

@Composable
private fun FavoriteLazyColumn(
    tabSelected: Int,
    uiState: FavoritesUiState,
    removeFavorite: (Long) -> Unit
) {
    val items = when (tabSelected) {
        0 -> uiState.favoriteTracks
        1 -> uiState.favoriteArtists
        2 -> uiState.favoriteAlbums
        else -> uiState.favoriteTracks
    }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = items
        ) {
            FavoriteRowItem(it, removeFavorite)
        }
        if (items.isEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "No favorite"
                )
            }
        }
    }
}

@Composable
private fun FavoriteTabRow(
    tabSelected: Int,
    onTabSelected: (Int) -> Unit,
    titles: List<String>
) {
    TabRow(selectedTabIndex = tabSelected, divider = {}) {
        titles.forEachIndexed { index, title ->
            Tab(selected = tabSelected == index,
                onClick = { onTabSelected(index) },
                text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) })
        }
    }
}

@Composable
private fun FavoriteRowItem(it: SearchResultModel, removeFavorite: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(horizontal = 5.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(it.picture),
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
                text = it.title,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = it.subTitle,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Button(
            modifier = Modifier.padding(5.dp),
            onClick = { removeFavorite(it.id) }
        ) {
            Image(
                imageVector = Icons.Rounded.Favorite,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondaryContainer),
                contentDescription = ""
            )
        }
    }
}