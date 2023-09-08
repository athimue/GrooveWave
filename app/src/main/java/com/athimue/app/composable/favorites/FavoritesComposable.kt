package com.athimue.app.composable.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.athimue.app.composable.home.PopularTitle

@Composable
fun FavoritesComposable() {
    val titles = listOf("Tracks", "Artists", "Albums")
    var tabSelected by remember { mutableStateOf(0) }
    // val uiState = viewModel.searchUiState.collectAsState()
    Column {
        PopularTitle(title = "Favorites")
        TabRow(selectedTabIndex = tabSelected, divider = {}) {
            titles.forEachIndexed { index, title ->
                Tab(selected = tabSelected == index,
                    onClick = { tabSelected = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) })
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = listOf("MATHIEU")) {
                Text(text = it)
            }
        }
    }
}