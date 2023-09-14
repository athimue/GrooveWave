package com.athimue.app.composable.album

import com.athimue.domain.model.Album

data class AlbumUiState(
    var isLoading: Boolean = false,
    var album: Album? = null
)