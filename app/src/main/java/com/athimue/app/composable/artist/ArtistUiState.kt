package com.athimue.app.composable.artist

import com.athimue.domain.model.Artist

data class ArtistUiState(
    var isLoading: Boolean = false,
    var artist: Artist? = null
)