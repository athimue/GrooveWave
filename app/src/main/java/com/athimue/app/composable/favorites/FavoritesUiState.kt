package com.athimue.app.composable.favorites

import com.athimue.app.composable.search.SearchResultModel

internal data class FavoritesUiState(
    var isLoading: Boolean = false,
    var favoriteTracks: List<SearchResultModel> = listOf(),
    var favoriteArtists: List<SearchResultModel> = listOf(),
    var favoriteAlbums: List<SearchResultModel> = listOf(),
)