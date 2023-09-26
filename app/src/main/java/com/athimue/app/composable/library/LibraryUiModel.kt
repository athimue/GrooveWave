package com.athimue.app.composable.library

import com.athimue.domain.model.Playlist

data class LibraryUiModel(
    val id: Int,
    val name: String,
    val tracksSize: Int
)

fun Playlist.toLibraryUiModel() = LibraryUiModel(
    id = id,
    name = name,
    tracksSize = tracks.size
)
