package com.athimue.app.composable.playlist

data class PlaylistUiModel(
    val id: Int,
    val name: String,
    val trackUiModels: List<TrackUiModel>
)