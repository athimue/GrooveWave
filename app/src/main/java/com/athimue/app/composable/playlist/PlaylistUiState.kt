package com.athimue.app.composable.playlist

data class PlaylistUiState(
    var playlist: PlaylistUiModel? = null,
    val trackUrlPlayed: String? = null
)