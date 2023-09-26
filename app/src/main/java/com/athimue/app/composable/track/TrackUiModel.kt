package com.athimue.app.composable.track

import com.athimue.domain.model.Track

data class TrackUiModel(
    val title: String,
    val cover: String,
    val duration: Int,
    val rank: Int,
    val explicitLyrics: Boolean,
    val position: Int,
    val artistId: String,
    val artistName: String,
    val artistPicture: String,
    val albumId: String,
    val albumTitle: String,
    val albumCover: String,
)

fun Track.toTrackUiModel() = TrackUiModel(
    title = title,
    cover = cover,
    duration = duration,
    rank = rank,
    explicitLyrics = explicitLyrics,
    position = position!!,
    artistId = artist.id.toString(),
    artistName = artist.name,
    artistPicture = artist.cover!!,
    albumId = album.id.toString(),
    albumTitle = album.name,
    albumCover = album.cover,
)
