package com.athimue.app.composable.playlist

import com.athimue.domain.model.Track

data class PlaylistUiModel(
    val id: Int,
    val name: String,
    val tracks: List<Track>
)