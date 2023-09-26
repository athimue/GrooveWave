package com.athimue.app.composable.album

data class AlbumUiState(
    var isLoading: Boolean = false,
    var album: AlbumUiModel? = null
)