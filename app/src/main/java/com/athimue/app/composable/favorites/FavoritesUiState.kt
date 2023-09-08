package com.athimue.app.composable.favorites

import com.athimue.app.composable.home.LazyRowItemModel

data class FavoritesUiState(
    var isLoading: Boolean = false,
    var favoriteTracks: List<LazyRowItemModel>? = null,
    var favoriteArtists: List<LazyRowItemModel>? = null,
    var favoriteAlbum: List<LazyRowItemModel>? = null,
)