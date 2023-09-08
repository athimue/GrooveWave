package com.athimue.app.composable.library

import com.athimue.domain.model.Playlist

data class LibraryUiState(
    var playlist: List<Playlist>? = null,
)