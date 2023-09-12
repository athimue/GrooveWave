package com.athimue.app.composable.favorites

import com.athimue.app.composable.search.SearchResultModel

data class FavoritesUiState(
    var isLoading: Boolean = false,
    var favoriteTracks: List<SearchResultModel> = listOf(),
    var favoriteArtists: List<SearchResultModel> = listOf(),
    var favoriteAlbum: List<SearchResultModel> = listOf(),
)