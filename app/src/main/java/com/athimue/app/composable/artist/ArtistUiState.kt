package com.athimue.app.composable.artist

data class ArtistUiState(
    var isLoading: Boolean = false,
    var artist: ArtistUiModel? = null
)