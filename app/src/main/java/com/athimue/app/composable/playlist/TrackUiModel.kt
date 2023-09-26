package com.athimue.app.composable.playlist

import com.athimue.domain.model.Track

data class TrackUiModel(
    val id: Long,
    val title: String,
    val duration: Int,
    val preview: String,
    val cover: String,
    val artistName: String,
    val albumName: String
)

fun Track.toTrackUiModel() = TrackUiModel(
    id = id,
    title = title,
    duration = duration,
    preview = preview,
    cover = cover,
    artistName = artist.name,
    albumName = album.name
)
